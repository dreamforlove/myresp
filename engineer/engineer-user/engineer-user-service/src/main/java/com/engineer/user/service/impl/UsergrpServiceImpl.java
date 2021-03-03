package com.engineer.user.service.impl;

import com.engineer.common.enums.impl.EnabledEnum;
import com.engineer.common.enums.impl.UsergrpExceptionEnum;
import com.engineer.common.enums.impl.VerifyEnum;
import com.engineer.common.exceptions.ServiceException;
import com.engineer.common.utils.IdWorker;
import com.engineer.common.vo.PageResult;
import com.engineer.user.mapper.*;
import com.engineer.user.pojo.UserUsergrp;
import com.engineer.user.pojo.Usergrp;
import com.engineer.user.pojo.UsergrpRole;
import com.engineer.user.service.UsergrpService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;

/**
 * @author Lemon
 * @date 2020/3/16 15:56
 */
@Slf4j
@Service
public class UsergrpServiceImpl implements UsergrpService {

    @Autowired
    IdWorker idWorker;

    @Autowired
    UsergrpMapper usergrpMapper;
    @Autowired
    UserUsergrpMapper userUsergrpMapper;
    @Autowired
    UsergrpRoleMapper usergrpRoleMapper;
    @Autowired
    UserRoleMapper userRoleMapper;
    @Autowired
    OrganizationMapper organizationMapper;

    @Override
    public PageResult<Usergrp> getUsergrpListPageByOrgaId(Integer pageNum, Integer pageSize, Long orgaId) {
        PageHelper.startPage(pageNum, pageSize);
        Usergrp usergrp = new Usergrp();
        usergrp.setOrgaId(orgaId);
        List<Usergrp> list = usergrpMapper.select(usergrp);
        PageInfo<Usergrp> info = new PageInfo<>(list);
        PageResult<Usergrp> result = new PageResult<>();
        result.setTotal(info.getTotal());
        result.setPages(info.getPages());
        result.setPageNum(info.getPageNum());
        result.setRows(info.getList());
        return result;
    }

    @Override
    public void setUsergrpStatus(Long usergrpId, int value) {
        Usergrp usergrp = new Usergrp();
        usergrp.setUsergrpId(usergrpId);
        usergrp.setEnabled(value);
        int count = usergrpMapper.updateByPrimaryKeySelective(usergrp);
        if (count != 1) {
            log.error("修改用户组状态失败,用户组编号:{},修改的状态:{}", usergrpId, value);
            throw new ServiceException(UsergrpExceptionEnum.UPDATE_USERGRP_STATUS_FAIL);
        }
    }

    @Override
    public void setUsergrpVerify(Long usergrpId, int value) {
        Usergrp usergrp = new Usergrp();
        usergrp.setUsergrpId(usergrpId);
        usergrp.setVerify(value);
        int count = usergrpMapper.updateByPrimaryKeySelective(usergrp);
        if (count != 1) {
            log.error("修改用户组审核状态失败,用户组编号:{},修改的状态:{}", usergrpId, value);
            throw new ServiceException(UsergrpExceptionEnum.UPDATE_USERGRP_VERIFY_FAIL);
        }
    }

    @Override
    public void addUsergrp(Usergrp usergrp) {
        if (
            StringUtils.isBlank(usergrp.getUsergrpName()) || usergrp.getUsergrpType() == null
            || StringUtils.isBlank(usergrp.getPrincipal()) || StringUtils.isBlank(usergrp.getPhone())
            || StringUtils.isBlank(usergrp.getGrpDesc()) || usergrp.getOrgaId() == null
            || usergrp.getCreaterId() == null
        ) {
            throw new ServiceException(UsergrpExceptionEnum.ADD_USERGRP_PARAM_ERROR);
        }
        usergrp.setUsergrpId(idWorker.nextId());
        usergrp.setCreateTime(new Date());
        usergrp.setEnabled(EnabledEnum.ENABLED.getValue());
        usergrp.setVerify(VerifyEnum.CHECK_PENDING.getValue());
        int count = usergrpMapper.insert(usergrp);
        if (count != 1) {
            log.error("新增用户组失败");
            throw new ServiceException(UsergrpExceptionEnum.ADD_USERGRP_FAIL);
        }

    }

    @Override
    public void updateUsergrp(Usergrp usergrp) {
        usergrp.setEnabled(null);
        usergrp.setVerify(null);
        usergrp.setOrgaId(null);
        usergrp.setCreaterId(null);
        usergrp.setCreateTime(null);
        usergrp.setProductid(null);
        int count = usergrpMapper.updateByPrimaryKeySelective(usergrp);
        if (count != 1) {
            log.error("修改用户组失败");
            throw new ServiceException(UsergrpExceptionEnum.UPDATE_USERGRP_FAIL);
        }
    }

    @Override
    @Transactional(rollbackFor = ServiceException.class)
    public void deleteUsergrpById(Long usergrpId) {
        UserUsergrp userUsergrp = new UserUsergrp();
        userUsergrp.setUsergrpId(usergrpId);
        UsergrpRole usergrpRole = new UsergrpRole();
        usergrpRole.setUsergrpId(usergrpId);
        userUsergrpMapper.delete(userUsergrp);
        usergrpRoleMapper.delete(usergrpRole);
        int count = usergrpMapper.deleteByPrimaryKey(usergrpId);
        if (count != 1) {
            throw new ServiceException(UsergrpExceptionEnum.DELETE_USERGRP_FAIL);
        }
    }

    @Override
    public Boolean getUsergrpAuthorizationStatus(Long usergrpId, Long userId) {
        if (usergrpId == null || userId == null) {
            return false;
        }
        UserUsergrp userUsergrp = new UserUsergrp();
        userUsergrp.setUsergrpId(usergrpId);
        userUsergrp.setUserId(userId);
        List<UserUsergrp> list = userUsergrpMapper.select(userUsergrp);
        return !CollectionUtils.isEmpty(list);
    }

    @Override
    public void authorization(UserUsergrp userUsergrp) {
        if (userUsergrp.getUserId() == null || userUsergrp.getUsergrpId() == null
                || userUsergrp.getCreaterId() == null || userUsergrp.getProductid() == null
        ) {
            throw new ServiceException(UsergrpExceptionEnum.PARAM_ERROR);
        }
        userUsergrp.setCreateTime(new Date());
        int insert = userUsergrpMapper.insert(userUsergrp);
        if (insert == 0) {
            throw new ServiceException(UsergrpExceptionEnum.AUTHORIZE_USERGRP_FAILED);
        }
    }

    @Override
    @Transactional(rollbackFor = ServiceException.class)
    public void cancelAuthorization(Long orgaId, Long usergrpId, Long userId) {
        userRoleMapper.deleteUserRole(orgaId, usergrpId, userId);
        UserUsergrp userUsergrp = new UserUsergrp();
        userUsergrp.setUsergrpId(usergrpId);
        userUsergrp.setUserId(userId);
        userUsergrp.setProductid(orgaId);
        int delete = userUsergrpMapper.delete(userUsergrp);
        if (delete != 1) {
            throw new ServiceException(UsergrpExceptionEnum.CANCEL_AUTHORIZE_USERGRP_FAILED);
        }
    }

    @Override
    public boolean checkUsergrpUserAuthorization(Long currentUserId, UserUsergrp userUsergrp) {
        // 进行操作需要的权限
        // 1.当前用户是该组织机构的创建者
        Long orgaId = userUsergrp.getProductid();
        Long createrId = organizationMapper.selectByPrimaryKey(orgaId).getCreaterId();
        return createrId.equals(currentUserId);
    }
}
