package com.engineer.user.service;

import com.engineer.common.vo.PageResult;
import com.engineer.user.pojo.Organization;
import com.engineer.user.vo.InvitedStaff;
import com.engineer.user.vo.UserOrgaVo;

import java.util.List;

/**
 * @author Lemon
 * @date 2020/3/14 20:44
 */
public interface OrganizationService {

    /**
     * 分页并根据关键词搜索组织机构列表
     * @param pageNum
     * @param pageSize
     * @param keywords
     * @param userId
     * @return
     */
    PageResult<Organization> getOrgaListByKeywords(Integer pageNum, Integer pageSize, String keywords, Long userId);

    /**
     * 分页获取组织机构详细信息
     * @param pageNum
     * @param pageSize
     * @param verify
     * @param keywordsType
     * @param keywords
     * @param userId
     * @return
     */
    PageResult<Organization> getPagedOrgaManagementList(Integer pageNum, Integer pageSize, String verify, String keywordsType, String keywords, Long userId);

    /**
     * 根据组织机构编号禁用/启用组织机构
     * @param orgaId
     * @param value
     */
    void setOrgaStatus(Long orgaId, int value);

    /**
     * 根据组织机构编号设置组织机构审核状态
     * @param orgaId
     * @param value
     */
    void setOrgaVerify(Long orgaId, int value);

    /**
     * 更新组织机构信息
     * @param organization
     */
    void updateOrga(Organization organization);

    /**
     * 根据用户编号获取该用户创建的组织机构
     * @param userId
     * @return
     */
    List<Organization> getCreatedOrgaByUserId(Long userId);

    /**
     * 根据用户编号获取该用户加入的组织机构
     * @param userId
     * @return
     */
    List<Organization> getJoinedOrgaByUserId(Long userId);

    /**
     * 认证新的组织机构
     * @param organization
     */
    void addOrga(Organization organization);

    /**
     * 根据组织机构编号删除组织机构及员工关系
     * @param orgaId
     */
    void delOrga(Long orgaId);

    /**
     * 根据用户编号获取受邀记录
     *
     * @param pageNum
     * @param pageSize
     * @param userId
     * @return
     */
    PageResult<UserOrgaVo> getInvitedListByUserId(Integer pageNum, Integer pageSize, Long userId);

    /**
     * 设置入职状态
     * @param userOrgaId
     * @param value
     */
    void setUserOrgaStatus(Long userOrgaId, int value);

    /**
     * 获取员工信息
     * @param pageNum
     * @param pageSize
     * @param orgaId
     * @param keywordsType
     * @param keywords
     * @return
     */
    PageResult<InvitedStaff> getStaffList(Integer pageNum, Integer pageSize, Long orgaId, String keywordsType, String keywords);

    /**
     * 邀请新员工
     * @param orgaId
     * @param userId
     */
    void inviteStaff(Long orgaId, Long userId);

    /**
     * 检查当前用户是否有权限修改该组织机构信息
     * @param userId
     * @param orgaId
     * @return
     */
    boolean checkUserIsOrgaCreater(Long userId, Long orgaId);

    /**
     * 检查当前用户是否有让员工离职的权限
     * @param userId
     * @param userOrgaId
     * @return
     */
    boolean checkResign(Long userId, Long userOrgaId);
}
