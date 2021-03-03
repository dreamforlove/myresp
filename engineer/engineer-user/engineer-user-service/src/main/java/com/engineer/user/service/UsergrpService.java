package com.engineer.user.service;

import com.engineer.common.vo.PageResult;
import com.engineer.user.pojo.UserUsergrp;
import com.engineer.user.pojo.Usergrp;

/**
 * @author Lemon
 * @date 2020/3/16 15:52
 */
public interface UsergrpService {
    /**
     * 分页根据组织机构编号查询用户组列表
     * @param pageNum
     * @param pageSize
     * @param orgaId
     * @return
     */
    PageResult<Usergrp> getUsergrpListPageByOrgaId(Integer pageNum, Integer pageSize, Long orgaId);

    /**
     * 根据用户组编号启用/禁用用户组
     * @param usergrpId
     * @param value
     */
    void setUsergrpStatus(Long usergrpId, int value);

    /**
     * 根据用户组编号设置用户组审核状态
     * @param usergrpId
     * @param value
     */
    void setUsergrpVerify(Long usergrpId, int value);

    /**
     * 新增用户组
     * @param usergrp
     */
    void addUsergrp(Usergrp usergrp);

    /**
     * 修改用户组基本信息
     * @param usergrp
     */
    void updateUsergrp(Usergrp usergrp);

    /**
     * 根据用户组编号删除用户组及用户组与角色的关联信息
     * @param usergrpId
     */
    void deleteUsergrpById(Long usergrpId);

    /**
     * 根据用户组编号和用户编号获取该用户是否授权该用户组
     * @param usergrpId
     * @param userId
     * @return
     */
    Boolean getUsergrpAuthorizationStatus(Long usergrpId, Long userId);

    /**
     * 授权用户组给用户
     * @param userUsergrp
     */
    void authorization(UserUsergrp userUsergrp);

    /**
     * 取消用户的用户组授权
     * @param orgaId
     * @param usergrpId
     * @param userId
     */
    void cancelAuthorization(Long orgaId, Long usergrpId, Long userId);

    /**
     * 检查当前用户是否有权限进行授权/取消用户组给用户的操作
     * @param currentUserId 当前操作用户
     * @param userUsergrp 被操作的 用户-用户组 信息
     * @return
     */
    boolean checkUsergrpUserAuthorization(Long currentUserId, UserUsergrp userUsergrp);
}
