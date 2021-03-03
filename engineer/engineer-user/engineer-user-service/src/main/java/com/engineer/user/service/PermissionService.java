package com.engineer.user.service;

import com.engineer.common.vo.PageResult;
import com.engineer.user.pojo.Menu;
import com.engineer.user.pojo.Resource;
import com.engineer.user.pojo.RolePermission;
import com.engineer.user.vo.MenuVo;
import com.engineer.user.vo.ResourceVo;


/**
 * @author Lemon
 * @date 2020/3/23 20:00
 */
public interface PermissionService {
    /**
     * 根据角色编号分页获取已授权的菜单权限
     * @param pageNum
     * @param pageSize
     * @param roleId
     * @return
     */
    PageResult<MenuVo> getPagedAuthorizedMenuListByRoleId(Integer pageNum, Integer pageSize, Long roleId);

    /**
     * 根据角色编号分页获取未授权的菜单权限
     * @param pageNum
     * @param pageSize
     * @param roleId
     * @return
     */
    PageResult<MenuVo> getPagedUnauthorizedMenuListByRoleId(Integer pageNum, Integer pageSize, Long roleId);

    /**
     * 根据角色编号和权限编号取消授权
     * @param roleId
     * @param permissionId
     */
    void cancelAuthorization(Long roleId, Long permissionId);

    /**
     * 根据角色编号和权限编号授权
     * @param rolePermission
     */
    void authorization(RolePermission rolePermission);

    /**
     * 分页获取菜单权限列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageResult<MenuVo> getPagedMenuList(Integer pageNum, Integer pageSize);

    /**
     * 分页获取功能权限列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageResult<ResourceVo> getPagedResourceList(Integer pageNum, Integer pageSize);

    /**
     * 根据权限编号启用/禁用权限
     * @param permissionId
     * @param value
     */
    void setPermissionStatus(Long permissionId, int value);

    /**
     * 根据权限编号删除权限
     * @param permissionId
     */
    void deletePermissionById(Long permissionId);

    /**
     * 添加新的菜单权限
     * @param menuVo
     */
    void addMenuPermission(MenuVo menuVo);

    /**
     * 添加新的功能权限
     * @param resourceVo
     */
    void addResourcePermission(ResourceVo resourceVo);

    /**
     * 根据菜单编号修改菜单权限
     * @param menu
     */
    void updateMenuPermission(Menu menu);

    /**
     * 根据功能编号修改功能权限
     * @param resource
     */
    void updateResourcePermission(Resource resource);

    /**
     * 根据角色编号分页获取已授权的功能权限
     * @param pageNum
     * @param pageSize
     * @param roleId
     * @return
     */
    PageResult<ResourceVo> getPagedAuthorizedResourceListByRoleId(Integer pageNum, Integer pageSize, Long roleId);

    /**
     * 根据角色编号分页获取未授权的功能权限
     * @param pageNum
     * @param pageSize
     * @param roleId
     * @return
     */
    PageResult<ResourceVo> getPagedUnauthorizedResourceListByRoleId(Integer pageNum, Integer pageSize, Long roleId);
}
