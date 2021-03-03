package com.engineer.user.mapper;

import com.engineer.common.mapper.BaseMapper;
import com.engineer.user.pojo.UserRole;
import org.apache.ibatis.annotations.Param;

public interface UserRoleMapper extends BaseMapper<UserRole> {
    /**
     * 删除某个用户在某个组织机构某个用户组下的角色关联
     *
     * @param orgaId
     * @param usergrpId
     * @param userId
     */
    void deleteUserRole(@Param("orgaId") Long orgaId, @Param("usergrpId") Long usergrpId, @Param("userId") Long userId);
}