package com.engineer.user.service;

import com.engineer.common.vo.PageResult;
import com.engineer.user.pojo.User;
import com.engineer.user.vo.RegisterVo;
import com.engineer.user.vo.UserDetail;
import com.engineer.user.vo.UserExt;

/**
 * @author Lemon
 * @date 2019/9/27 20:20
 */
public interface UserService {

    /**
     * 根据用户账号获取用户基本信息
     * @param userAccount
     * @return
     */
    User findUserByUserAccount(String userAccount);

    /**
     * 根据用户账号获取用户信息及权限信息
     * @param userAccount
     * @return
     */
    UserExt getUserExt(String userAccount);

    /**
     * 分页获取用户信息列表
     * @param pageNum
     * @param pageSize
     * @param keywordsType
     * @param keywords
     * @return
     */
    PageResult<User> getUserListByPage(Integer pageNum, Integer pageSize, String keywordsType, String keywords);

    /**
     * 设置用户账号状态
     * @param userId
     * @param value
     */
    void setUserStatus(Long userId, int value);

    /**
     * 根据用户编号获取用户资料详情
     * @param userId
     * @return
     */
    UserDetail getUserDetail(Long userId);

    /**
     * 保存修改用户详细信息
     * @param userDetail
     */
    void updateUserDetail(UserDetail userDetail);

    /**
     * 校验用户名或手机号是否已注册
     * @param data
     * @param type
     * @return
     */
    Boolean checkUserData(String data, String type);

    /**
     * 发送短信验证码
     * @param phone
     */
    void sendCode(String phone);

    /**
     * 用户注册
     * @param registerVo
     */
    void register(RegisterVo registerVo);

    /**
     * 修改密码
     * @param userAccount
     * @param password
     * @param phone
     * @param code
     */
    void changePassword(String userAccount, String password, String phone, String code);
}
