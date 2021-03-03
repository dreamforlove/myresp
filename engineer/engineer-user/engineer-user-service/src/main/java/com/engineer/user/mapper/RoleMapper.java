package com.engineer.user.mapper;

import com.engineer.common.mapper.BaseMapper;
import com.engineer.user.pojo.Role;
import com.engineer.user.vo.UserRoleVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper extends BaseMapper<Role> {
    /**
     * 通过用户编号查询用户角色列表
     *
     * @param userId
     * @return
     */
    List<Role> selectRolesByUserId(@Param("userId") Long userId);

    /**
     * 通过用户组编号查询已授权角色列表
     *
     * @param usergrpId
     * @return
     */
    List<Role> getAuthorizedRoleListByUsergrpId(@Param("usergrpId") Long usergrpId);

    /**
     * 根据用户组编号获取未授权的角色列表
     *
     * @param usergrpId
     * @return
     */
    List<Role> getUnauthorizedRoleListByUsergrpId(@Param("usergrpId") Long usergrpId);

    /**
     * 根据关键字获取角色列表
     *
     * @param keywordsType
     * @param keywords
     * @return
     */
    List<Role> selectRoleList(@Param("keywordsType") String keywordsType, @Param("keywords") String keywords);

    /**
     * 获取该用户组下该用户的角色授权情况
     *
     * @param tmpUserRole
     * @param usergrpId
     * @param userId
     * @return
     */
    List<UserRoleVo> getUserRoleListFromUsergrp(@Param("tmpUserRole") String tmpUserRole, @Param("usergrpId") Long usergrpId, @Param("userId") Long userId);

    /**
     * 根据用户编号创建临时用户角色关联表
     *
     * @param tmpUserRole
     * @param userId
     */
    void createTmpUserRole(@Param("tmpUserRole") String tmpUserRole, @Param("userId") Long userId);

    void dropTmpUserRole(@Param("tmpUserRole") String tmpUserRole);
}