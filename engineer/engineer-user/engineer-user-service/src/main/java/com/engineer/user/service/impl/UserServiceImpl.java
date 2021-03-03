package com.engineer.user.service.impl;

import com.engineer.common.enums.impl.EnabledEnum;
import com.engineer.common.enums.impl.UserExceptionEnum;
import com.engineer.common.enums.impl.VerifyEnum;
import com.engineer.common.exceptions.ServiceException;
import com.engineer.common.utils.IdWorker;
import com.engineer.common.utils.NumberUtils;
import com.engineer.common.vo.PageResult;
import com.engineer.user.config.SendCodeProperties;
import com.engineer.user.mapper.*;
import com.engineer.user.pojo.*;
import com.engineer.user.service.LocationService;
import com.engineer.user.service.UserService;
import com.engineer.user.vo.RegisterVo;
import com.engineer.user.vo.UserDetail;
import com.engineer.user.vo.UserExt;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author Lemon
 * @date 2019/9/27 20:24
 */
@Slf4j
@Service
@EnableConfigurationProperties(SendCodeProperties.class)
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;
    @Autowired
    RoleMapper roleMapper;
    @Autowired
    MenuMapper menuMapper;
    @Autowired
    MProfileMapper mProfileMapper;
    @Autowired
    LocationMapper locationMapper;
    @Autowired
    ResourceMapper resourceMapper;

    @Autowired
    LocationService locationService;

    @Autowired
    private AmqpTemplate amqpTemplate;
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private SendCodeProperties prop;
    @Autowired
    private IdWorker idWorker;

    private static final String KEY_PREFIX = "user:verify:phone:";

    @Override
    public User findUserByUserAccount(String userAccount) {
        return userMapper.findUserByUserAccount(userAccount);
    }

    @Override
    @Transactional(rollbackFor = ServiceException.class)
    public UserExt getUserExt(String userAccount) {
        User user = this.findUserByUserAccount(userAccount);
        if (user == null){
            return null;
        }
        UserExt userExt = new UserExt();
        BeanUtils.copyProperties(user, userExt);
        Long userId = userExt.getUserId();
        // 查询用户权限
        List<Role> roles = roleMapper.selectRolesByUserId(userId);
        if (!CollectionUtils.isEmpty(roles)){
            List<String> menu = menuMapper.selectMenusByRoleList(roles);
            userExt.setMenu(menu);
            List<String> resource = resourceMapper.selectResourcesByRoleList(roles);
            userExt.setResource(resource);
        }
        return userExt;
    }

    @Override
    public PageResult<User> getUserListByPage(Integer pageNum, Integer pageSize, String keywordsType, String keywords) {
        PageHelper.startPage(pageNum, pageSize);
        List<User> list = userMapper.selectUserList(keywordsType, keywords);
        if (CollectionUtils.isEmpty(list)) {
            throw new ServiceException(UserExceptionEnum.USER_LIST_NOT_FOUND);
        }
        PageInfo<User> info = new PageInfo<>(list);
        PageResult<User> result = new PageResult<>();
        result.setTotal(info.getTotal());
        result.setPages(info.getPages());
        result.setPageNum(info.getPageNum());
        result.setRows(info.getList());
        return result;
    }

    @Override
    public void setUserStatus(Long userId, int value) {
        User user = new User();
        user.setUserId(userId);
        user.setEnabled(value);
        int count = userMapper.updateByPrimaryKeySelective(user);
        if (count != 1) {
            log.error("修改用户状态失败,用户编号:{},修改的状态:{}", userId, value);
            throw new ServiceException(UserExceptionEnum.UPDATE_USER_STATUS_FAIL);
        }
    }

    @Override
    public UserDetail getUserDetail(Long userId) {
        UserDetail userDetail = new UserDetail();
        User user = userMapper.selectByPrimaryKey(userId);
        if (user == null) {
            throw new ServiceException(UserExceptionEnum.USER_NOT_FOUND);
        }
        user.setPassword(null);
        BeanUtils.copyProperties(user, userDetail);
        MProfile mProfile = mProfileMapper.selectByPrimaryKey(userId);
        if (mProfile != null) {
            userDetail.setMProfile(mProfile);
            Integer locId = mProfile.getLocId();
            if (locId != null) {
                List<Integer> locationIds = locationService.getLocIdArrayList(locId);
                userDetail.setLocation(locationIds);
            }
        }
        return userDetail;
    }

    @Override
    @Transactional(rollbackFor = ServiceException.class)
    public void updateUserDetail(UserDetail userDetail) {
        User user = new User();
        BeanUtils.copyProperties(userDetail, user);
        MProfile mProfile = userDetail.getMProfile();
        mProfile.setUserId(user.getUserId());
        List<Integer> location = userDetail.getLocation();
        if (location.size() > 1) {
            mProfile.setLocId(location.get(location.size() - 1));
        } else {
            mProfile.setLocId(null);
        }
        int count = userMapper.updateByPrimaryKeySelective(user);
        if (count != 1) {
            log.error("修改用户资料失败,用户编号:{}", user.getUserId());
            throw new ServiceException(UserExceptionEnum.UPDATE_USER_ERROR);
        }
        int count1 = mProfileMapper.updateByPrimaryKeySelective(mProfile);
        if (count1 != 1) {
            log.error("修改用户详情失败,用户编号:{}", user.getUserId());
            throw new ServiceException(UserExceptionEnum.UPDATE_USER_ERROR);
        }
    }

    @Override
    public Boolean checkUserData(String data, String type) {
        User user = new User();
        switch (type) {
            case "userAccount":
                user.setUserAccount(data);
                break;
            case "phone":
                user.setPhone(data);
                break;
            default:
                throw new ServiceException(UserExceptionEnum.PARAM_ERROR);
        }
        return userMapper.selectCount(user) == 0;
    }

    @Override
    public void sendCode(String phone) {
        // 生成key
        String key = KEY_PREFIX + phone;
        // 生成验证码
        String code = NumberUtils.generateCode(6);
        Map<String, String> msg = new HashMap<>(16);
        msg.put("phone", phone);
        msg.put("code", code);
        // 发送验证码
        amqpTemplate.convertAndSend(prop.getExchange(), prop.getRoutingKey(), msg);
        // 保存验证码
        redisTemplate.opsForValue().set(key, code, prop.getCodeTimeOut(), TimeUnit.MINUTES);
    }

    @Override
    @Transactional(rollbackFor = ServiceException.class)
    public void register(RegisterVo registerVo) {
        String key = KEY_PREFIX + registerVo.getPhone();
        String redisCode = redisTemplate.opsForValue().get(key);
        if (!StringUtils.equals(registerVo.getCode(), redisCode)){
            log.info("[用户服务] 注册失败，验证码不存在，手机号：{},验证码：{}", registerVo.getPhone(), registerVo.getCode());
            throw new ServiceException(UserExceptionEnum.INVALID_CODE);
        }
        User user = new User();
        BeanUtils.copyProperties(registerVo, user);
        user.setUserId(idWorker.nextId());
        user.setPhoneVerified(VerifyEnum.ADOPTED.getValue());
        user.setEmailVerified(VerifyEnum.ADOPTED.getValue());
        user.setEnabled(EnabledEnum.ENABLED.getValue());
        user.setCreateTime(new Date());
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        int count = userMapper.insertSelective(user);
        if (count != 1) {
            throw new ServiceException(UserExceptionEnum.REGISTER_USER_FAILED);
        }
        MProfile mProfile = new MProfile();
        mProfile.setUserId(user.getUserId());
        mProfile.setCreateTime(user.getCreateTime());
        int insert = mProfileMapper.insertSelective(mProfile);
        if (insert != 1) {
            throw new ServiceException(UserExceptionEnum.REGISTER_USER_FAILED);
        }
        redisTemplate.delete(key);
    }

    @Override
    public void changePassword(String userAccount, String password, String phone, String code) {
        String key = KEY_PREFIX + phone;
        String redisCode = redisTemplate.opsForValue().get(key);
        if (!StringUtils.equals(code, redisCode)){
            log.info("[用户服务] 注册失败，验证码不存在，手机号：{},验证码：{}", phone, code);
            throw new ServiceException(UserExceptionEnum.INVALID_CODE);
        }
        User user = new User();
        user.setUserAccount(userAccount);
        user.setPhone(phone);
        List<User> select = userMapper.select(user);
        if (CollectionUtils.isEmpty(select)) {
            throw new ServiceException(UserExceptionEnum.USER_NOT_FOUND);
        }
        user.setUserId(select.get(0).getUserId());
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(password));
        int update = userMapper.updateByPrimaryKeySelective(user);
        if (update != 1) {
            throw new ServiceException(UserExceptionEnum.CHANGE_PASSWORD_FAILED);
        }
    }
}
