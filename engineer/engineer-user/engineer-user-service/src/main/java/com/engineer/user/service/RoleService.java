package com.engineer.user.service;

import com.engineer.common.vo.PageResult;
import com.engineer.user.pojo.Role;
import com.engineer.user.pojo.UserRole;
import com.engineer.user.pojo.UsergrpRole;
import com.engineer.user.vo.UserRoleVo;

/**
 * @author Lemon
 * @date 2020/3/20 15:11
 */
public interface RoleService {

    /**
     * 根据用户组编号获取已授权的角色列表
     * @return
     * @param pageNum
     * @param pageSize
     * @param usergrpId
     */
    PageResult<Role> getPagedAuthorizedRoleListByUsergrpId(Integer pageNum, Integer pageSize, Long usergrpId);

    /**
     * 根据用户组编号获取未授权的角色列表
     * @return
     * @param pageNum
     * @param pageSize
     * @param usergrpId
     */
    PageResult<Role> getPagedUnauthorizedRoleListByUsergrpId(Integer pageNum, Integer pageSize, Long usergrpId);

    /**
     * 取消用户组角色授权
     * @param usergrpId
     * @param roleId
     */
    void cancelAuthorization(Long usergrpId, Long roleId);

    /**
     * 授权角色给用户组
     * @param usergrpRole
     */
    void authorization(UsergrpRole usergrpRole);

    /**
     * 分页并根据关键字获取角色列表
     * @param pageNum
     * @param pageSize
     * @param keywordsType
     * @param keywords
     * @return
     */
    PageResult<Role> getRoleList(Integer pageNum, Integer pageSize, String keywordsType, String keywords);

    /**
     * 根据角色编号启用/禁用角色
     * @param roleId
     * @param value
     */
    void setRoleStatus(Long roleId, int value);

    /**
     * 根据角色编号删除角色
     * @param roleId
     */
    void deleteRoleById(Long roleId);

    /**
     * 新增角色
     * @param role
     */
    void addRole(Role role);

    /**
     * 修改角色
     * @param role
     */
    void updateRole(Role role);

    /**
     * 分页查询该用户组下该用户的角色授权情况
     * @param pageNum
     * @param pageSize
     * @param usergrpId
     * @param userId
     * @return
     */
    PageResult<UserRoleVo> getPagedUserRoleListFromUsergrp(Integer pageNum, Integer pageSize, Long usergrpId, Long userId);

    /**
     * 取消用户的角色授权
     * @param orgaId
     * @param roleId
     * @param userId
     */
    void cancelUserAuthorization(Long orgaId, Long roleId, Long userId);

    /**
     * 为用户添加角色授权
     * @param userRole
     */
    void userAuthorization(UserRole userRole);

    /**
     * 检查当前用户是否有权限为该用户授权/取消授权该角色
     * @param userId
     * @param userRole
     * @return
     */
    boolean checkRoleUserAuthorization(Long userId, UserRole userRole);
}
