package com.engineer.user.service.impl;

import com.engineer.common.enums.impl.EnabledEnum;
import com.engineer.common.enums.impl.RoleExceptionEnum;
import com.engineer.common.exceptions.ServiceException;
import com.engineer.common.utils.IdWorker;
import com.engineer.common.vo.PageResult;
import com.engineer.user.mapper.*;
import com.engineer.user.pojo.Role;
import com.engineer.user.pojo.RolePermission;
import com.engineer.user.pojo.UserRole;
import com.engineer.user.pojo.UsergrpRole;
import com.engineer.user.service.RoleService;
import com.engineer.user.vo.UserRoleVo;
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
 * @date 2020/3/20 15:11
 */
@Slf4j
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    IdWorker idWorker;

    @Autowired
    RoleMapper roleMapper;
    @Autowired
    UsergrpRoleMapper usergrpRoleMapper;
    @Autowired
    UserRoleMapper userRoleMapper;
    @Autowired
    RolePermissionMapper rolePermissionMapper;
    @Autowired
    OrganizationMapper organizationMapper;


    @Override
    public PageResult<Role> getPagedAuthorizedRoleListByUsergrpId(Integer pageNum, Integer pageSize, Long usergrpId) {
        PageHelper.startPage(pageNum, pageSize);
        List<Role> list = roleMapper.getAuthorizedRoleListByUsergrpId(usergrpId);
        return getRolePageResult(list);
    }

    @Override
    public PageResult<Role> getPagedUnauthorizedRoleListByUsergrpId(Integer pageNum, Integer pageSize, Long usergrpId) {
        PageHelper.startPage(pageNum, pageSize);
        List<Role> list = roleMapper.getUnauthorizedRoleListByUsergrpId(usergrpId);
        return getRolePageResult(list);
    }

    @Override
    public void cancelAuthorization(Long usergrpId, Long roleId) {
        if (usergrpId == null || roleId == null) {
            throw new ServiceException(RoleExceptionEnum.PARAM_ERROR);
        }
        UsergrpRole usergrpRole = new UsergrpRole();
        usergrpRole.setUsergrpId(usergrpId);
        usergrpRole.setRoleId(roleId);
        int count = usergrpRoleMapper.delete(usergrpRole);
        if (count != 1) {
            throw new ServiceException(RoleExceptionEnum.DELETE_USERGRP_ROLE_ERROR);
        }
    }

    @Override
    public void authorization(UsergrpRole usergrpRole) {
        if (usergrpRole.getUsergrpId() == null || usergrpRole.getRoleId() == null
                || usergrpRole.getCreaterId() == null) {
            throw new ServiceException(RoleExceptionEnum.PARAM_ERROR);
        }
        usergrpRole.setCreateTime(new Date());
        int id = usergrpRoleMapper.insert(usergrpRole);
        if (id == 0) {
            throw new ServiceException(RoleExceptionEnum.INSERT_USERGRP_ROLE_ERROR);
        }
    }

    @Override
    public PageResult<Role> getRoleList(Integer pageNum, Integer pageSize, String keywordsType, String keywords) {
        PageHelper.startPage(pageNum, pageSize);
        List<Role> list = roleMapper.selectRoleList(keywordsType, keywords);
        return getRolePageResult(list);
    }

    @Override
    public void setRoleStatus(Long roleId, int value) {
        Role role = new Role();
        role.setRoleId(roleId);
        role.setEnabled(value);
        int count = roleMapper.updateByPrimaryKeySelective(role);
        if (count != 1) {
            log.error("修改角色状态失败,角色编号:{},修改的状态:{}", roleId, value);
            throw new ServiceException(RoleExceptionEnum.UPDATE_ROLE_STATUS_FAILED);
        }
    }

    @Override
    @Transactional(rollbackFor = ServiceException.class)
    public void deleteRoleById(Long roleId) {
        UsergrpRole usergrpRole = new UsergrpRole();
        usergrpRole.setRoleId(roleId);
        UserRole userRole = new UserRole();
        userRole.setRoleId(roleId);
        RolePermission rolePermission = new RolePermission();
        rolePermission.setRoleId(roleId);
        usergrpRoleMapper.delete(usergrpRole);
        userRoleMapper.delete(userRole);
        rolePermissionMapper.delete(rolePermission);
        int count = roleMapper.deleteByPrimaryKey(roleId);
        if (count != 1) {
            throw new ServiceException(RoleExceptionEnum.DELETE_ROLE_FAILED);
        }
    }

    @Override
    public void addRole(Role role) {
        if (StringUtils.isBlank(role.getRoleName()) || StringUtils.isBlank(role.getRoleDesc())
                || role.getSequence() == null || role.getCreaterId() == null
        ){
            throw new ServiceException(RoleExceptionEnum.PARAM_ERROR);
        }
        role.setRoleId(idWorker.nextId());
        role.setCreateTime(new Date());
        role.setEnabled(EnabledEnum.ENABLED.getValue());
        int count = roleMapper.insert(role);
        if (count != 1) {
            throw new ServiceException(RoleExceptionEnum.ADD_ROLE_FAILED);
        }
    }

    @Override
    public void updateRole(Role role) {
        role.setCreaterId(null);
        role.setCreateTime(null);
        role.setEnabled(null);
        int count = roleMapper.updateByPrimaryKeySelective(role);
        if (count != 1) {
            throw new ServiceException(RoleExceptionEnum.UPDATE_ROLE_FAILED);
        }
    }

    @Override
    @Transactional(rollbackFor = ServiceException.class)
    public PageResult<UserRoleVo> getPagedUserRoleListFromUsergrp(Integer pageNum, Integer pageSize, Long usergrpId, Long userId) {
        String tmpUserRole = "tmp_user_role_" + userId;
        roleMapper.dropTmpUserRole(tmpUserRole);
        roleMapper.createTmpUserRole(tmpUserRole, userId);
        PageHelper.startPage(pageNum, pageSize);
        List<UserRoleVo> list = roleMapper.getUserRoleListFromUsergrp(tmpUserRole, usergrpId, userId);
        PageInfo<UserRoleVo> info = new PageInfo<>(list);
        PageResult<UserRoleVo> result = new PageResult<>();
        result.setTotal(info.getTotal());
        result.setPageNum(info.getPageNum());
        result.setPages(info.getPages());
        result.setRows(info.getList());
        return result;
    }

    @Override
    public void cancelUserAuthorization(Long orgaId, Long roleId, Long userId) {
        UserRole userRole = new UserRole();
        userRole.setRoleId(roleId);
        userRole.setUserId(userId);
        userRole.setProductid(orgaId);
        int delete = userRoleMapper.delete(userRole);
        if (delete != 1) {
            throw new ServiceException(RoleExceptionEnum.DELETE_USERGRP_ROLE_ERROR);
        }
    }

    @Override
    public void userAuthorization(UserRole userRole) {
        Long createrId = userRole.getCreaterId();
        userRole.setCreaterId(null);
        List<UserRole> select = userRoleMapper.select(userRole);
        if (!CollectionUtils.isEmpty(select)) {
            throw new ServiceException(RoleExceptionEnum.PARAM_ERROR);
        }
        userRole.setCreaterId(createrId);
        userRole.setCreateTime(new Date());
        int insert = userRoleMapper.insert(userRole);
        if (insert == 0) {
            throw new ServiceException(RoleExceptionEnum.INSERT_USERGRP_ROLE_ERROR);
        }
    }

    @Override
    public boolean checkRoleUserAuthorization(Long userId, UserRole userRole) {
        Long orgaId = userRole.getProductid();
        Long createrId = organizationMapper.selectByPrimaryKey(orgaId).getCreaterId();
        return createrId.equals(userId);
    }

    private PageResult<Role> getRolePageResult(List<Role> list) {
        PageInfo<Role> info = new PageInfo<>(list);
        PageResult<Role> result = new PageResult<>();
        result.setTotal(info.getTotal());
        result.setPageNum(info.getPageNum());
        result.setPages(info.getPages());
        result.setRows(info.getList());
        return result;
    }


}
