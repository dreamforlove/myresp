package com.engineer.user.mapper;

import com.engineer.common.mapper.BaseMapper;
import com.engineer.user.pojo.Resource;
import com.engineer.user.pojo.Role;
import com.engineer.user.vo.ResourceVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ResourceMapper extends BaseMapper<Resource> {
    /**
     * 获取功能权限列表
     *
     * @return
     */
    List<ResourceVo> getResourceList();

    /**
     * 根据角色编号获取已授权的功能权限列表
     *
     * @param roleId
     * @return
     */
    List<ResourceVo> getAuthorizedResourceListByRoleId(@Param("roleId") Long roleId);

    /**
     * 根据角色编号获取未授权的功能权限列表
     *
     * @param roleId
     * @return
     */
    List<ResourceVo> getUnAuthorizedResourceListByRoleId(@Param("roleId") Long roleId);

    /**
     * 获取角色列表已授权的功能权限url集合
     *
     * @param roles
     * @return
     */
    List<String> selectResourcesByRoleList(@Param("roles") List<Role> roles);
}