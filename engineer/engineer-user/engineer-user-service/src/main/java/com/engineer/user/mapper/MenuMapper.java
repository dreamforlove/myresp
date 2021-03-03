package com.engineer.user.mapper;

import com.engineer.common.mapper.BaseMapper;
import com.engineer.user.pojo.Menu;
import com.engineer.user.pojo.Role;
import com.engineer.user.vo.MenuVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MenuMapper extends BaseMapper<Menu> {
    /**
     * 根据角色集合查询角色下拥有的菜单权限
     *
     * @param roles
     * @return
     */
    List<String> selectMenusByRoleList(@Param("roles") List<Role> roles);

    /**
     * 根据角色编号获取已授权的菜单权限
     *
     * @param roleId
     * @return
     */
    List<MenuVo> getAuthorizedMenuListByRoleId(@Param("roleId") Long roleId);

    /**
     * 根据角色编号获取未授权的菜单权限
     *
     * @param roleId
     * @return
     */
    List<MenuVo> getUnAuthorizedMenuListByRoleId(@Param("roleId") Long roleId);

    /**
     * 获取菜单权限列表
     * @return
     */
    List<MenuVo> getMenuList();
}