package com.engineer.user.mapper;

import com.engineer.common.mapper.BaseMapper;
import com.engineer.user.pojo.Organization;
import com.engineer.user.vo.InvitedStaff;
import com.engineer.user.vo.UserOrgaVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrganizationMapper extends BaseMapper<Organization> {
    /**
     * 通过关键词搜索组织机构列表
     *
     * @param keywords
     * @param userId
     * @return
     */
    List<Organization> getOrgaListByKeywords(@Param("keywords") String keywords, @Param("userId") Long userId);

    /**
     * 获取组织机构详细信息列表
     *
     * @param verify
     * @param keywordsType
     * @param keywords
     * @param userId
     * @return
     */
    List<Organization> getOrgaManagementList(@Param("verify") String verify, @Param("keywordsType") String keywordsType, @Param("keywords") String keywords, @Param("userId") Long userId);

    /**
     * 根据用户编号获取该用户加入的组织机构
     *
     * @param userId
     * @return
     */
    List<Organization> getJoinedOrgaByUserId(@Param("userId") Long userId);

    /**
     * 根据用户编号获取受邀记录
     *
     * @param userId
     * @return
     */
    List<UserOrgaVo> getInvitedListByUserId(@Param("userId") Long userId);

    /**
     * 根据组织机构编号获取员工信息
     *
     * @param orgaId
     * @param keywordsType
     * @param keywords
     * @return
     */
    List<InvitedStaff> getStaffList(@Param("orgaId") Long orgaId, @Param("keywordsType") String keywordsType, @Param("keywords") String keywords);
}