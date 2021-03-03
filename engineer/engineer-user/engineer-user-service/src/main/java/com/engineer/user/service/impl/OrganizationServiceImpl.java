package com.engineer.user.service.impl;

import com.engineer.common.enums.impl.EnabledEnum;
import com.engineer.common.enums.impl.OrgaExceptionEnum;
import com.engineer.common.enums.impl.VerifyEnum;
import com.engineer.common.enums.impl.WorkingStatusEnum;
import com.engineer.common.exceptions.ServiceException;
import com.engineer.common.utils.IdWorker;
import com.engineer.common.vo.PageResult;
import com.engineer.user.mapper.OrganizationMapper;
import com.engineer.user.mapper.UserOrgaMapper;
import com.engineer.user.mapper.UsergrpMapper;
import com.engineer.user.pojo.Organization;
import com.engineer.user.pojo.UserOrga;
import com.engineer.user.pojo.Usergrp;
import com.engineer.user.service.OrganizationService;
import com.engineer.user.vo.InvitedStaff;
import com.engineer.user.vo.UserOrgaVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;

/**
 * @author Lemon
 * @date 2020/3/14 20:45
 */
@Service
public class OrganizationServiceImpl implements OrganizationService {

    @Autowired
    IdWorker idWorker;

    @Autowired
    OrganizationMapper organizationMapper;
    @Autowired
    UserOrgaMapper userOrgaMapper;
    @Autowired
    UsergrpMapper usergrpMapper;

    @Override
    public PageResult<Organization> getOrgaListByKeywords(Integer pageNum, Integer pageSize, String keywords, Long userId) {
        PageHelper.startPage(pageNum, pageSize);
        List<Organization> list = organizationMapper.getOrgaListByKeywords(keywords, userId);
        PageResult<Organization> result = getOrganizationPageResult(list);
        return result;
    }

    @Override
    public PageResult<Organization> getPagedOrgaManagementList(Integer pageNum, Integer pageSize, String verify, String keywordsType, String keywords, Long userId) {
        PageHelper.startPage(pageNum, pageSize);
        List<Organization> list = organizationMapper.getOrgaManagementList(verify, keywordsType, keywords, userId);
        PageResult<Organization> result = getOrganizationPageResult(list);
        return result;
    }

    @Override
    public void setOrgaStatus(Long orgaId, int value) {
        Organization organization = new Organization();
        organization.setOrgaId(orgaId);
        organization.setEnabled(value);
        int count = organizationMapper.updateByPrimaryKeySelective(organization);
        if (count != 1) {
            throw new ServiceException(OrgaExceptionEnum.UPDATE_ORGA_STATUS_FAILED);
        }
    }

    @Override
    public void setOrgaVerify(Long orgaId, int value) {
        Organization organization = new Organization();
        organization.setOrgaId(orgaId);
        organization.setVerify(value);
        int count = organizationMapper.updateByPrimaryKeySelective(organization);
        if (count != 1) {
            throw new ServiceException(OrgaExceptionEnum.UPDATE_ORGA_VERIFY_FAILED);
        }
    }

    @Override
    public void updateOrga(Organization organization) {
        if (organization.getOrgaId() == null) {
            throw new ServiceException(OrgaExceptionEnum.PARAM_ERROR);
        }
        int count = organizationMapper.updateByPrimaryKeySelective(organization);
        if (count != 1) {
            throw new ServiceException(OrgaExceptionEnum.UPDATE_ORGA_FAILED);
        }
    }

    @Override
    public List<Organization> getCreatedOrgaByUserId(Long userId) {
        Organization organization = new Organization();
        organization.setCreaterId(userId);
        List<Organization> list = organizationMapper.select(organization);
        return list;
    }

    @Override
    public List<Organization> getJoinedOrgaByUserId(Long userId) {
        List<Organization> list = organizationMapper.getJoinedOrgaByUserId(userId);
        return list;
    }

    @Override
    public void addOrga(Organization organization) {
        organization.setOrgaId(idWorker.nextId());
        organization.setVerify(VerifyEnum.CHECK_PENDING.getValue());
        organization.setEnabled(EnabledEnum.DISABLED.getValue());
        organization.setSequence(0);
        organization.setCreateTime(new Date());
        int insert = organizationMapper.insert(organization);
        if (insert != 1) {
            throw new ServiceException(OrgaExceptionEnum.ADD_ORGA_FAILED);
        }
    }

    @Override
    @Transactional(rollbackFor = ServiceException.class)
    public void delOrga(Long orgaId) {
        UserOrga userOrga = new UserOrga();
        userOrga.setOrgaId(orgaId);
        userOrgaMapper.delete(userOrga);
        Usergrp usergrp = new Usergrp();
        usergrp.setOrgaId(orgaId);
        usergrpMapper.delete(usergrp);
        int count = organizationMapper.deleteByPrimaryKey(orgaId);
        if (count != 1) {
            throw new ServiceException(OrgaExceptionEnum.DELETE_ORGA_FAILED);
        }
    }

    @Override
    public PageResult<UserOrgaVo> getInvitedListByUserId(Integer pageNum, Integer pageSize, Long userId) {
        if (userId == null) {
            throw new ServiceException(OrgaExceptionEnum.PARAM_ERROR);
        }
        PageHelper.startPage(pageNum, pageSize);
        List<UserOrgaVo> list = organizationMapper.getInvitedListByUserId(userId);
        PageInfo<UserOrgaVo> info = new PageInfo<>(list);
        PageResult<UserOrgaVo> result = new PageResult<>();
        result.setTotal(info.getTotal());
        result.setPageNum(info.getPageNum());
        result.setPages(info.getPages());
        result.setRows(info.getList());
        return result;
    }

    @Override
    public void setUserOrgaStatus(Long userOrgaId, int value) {
        UserOrga userOrga = new UserOrga();
        userOrga.setId(userOrgaId);
        userOrga.setStatus(value);
        int count = userOrgaMapper.updateByPrimaryKeySelective(userOrga);
        if (count != 1) {
            throw new ServiceException(OrgaExceptionEnum.UPDATE_USER_ORGA_STATUS_FAILED);
        }
    }

    @Override
    public PageResult<InvitedStaff> getStaffList(Integer pageNum, Integer pageSize, Long orgaId, String keywordsType, String keywords) {
        PageHelper.startPage(pageNum, pageSize);
        List<InvitedStaff> list = organizationMapper.getStaffList(orgaId, keywordsType, keywords);
        PageInfo<InvitedStaff> info = new PageInfo<>(list);
        PageResult<InvitedStaff> result = new PageResult<>();
        result.setTotal(info.getTotal());
        result.setPageNum(info.getPageNum());
        result.setPages(info.getPages());
        result.setRows(info.getList());
        return result;
    }

    @Override
    public void inviteStaff(Long orgaId, Long userId) {
        UserOrga userOrga = new UserOrga();
        userOrga.setUserId(userId);
        userOrga.setOrgaId(orgaId);
        userOrga.setStatus(WorkingStatusEnum.ACCEPTED.getValue());
        List<UserOrga> list = userOrgaMapper.select(userOrga);
        if (!CollectionUtils.isEmpty(list)) {
            throw new ServiceException(OrgaExceptionEnum.USER_ORGA_IS_EXISTS);
        }
        userOrga.setStatus(WorkingStatusEnum.TO_BE_ACCEPTED.getValue());
        userOrga.setCreateTime(new Date());
        int insert = userOrgaMapper.insert(userOrga);
        if (insert == 0) {
            throw new ServiceException(OrgaExceptionEnum.INSERT_USER_ORGA_FAILED);
        }
    }

    @Override
    public boolean checkUserIsOrgaCreater(Long userId, Long orgaId) {
        Long createrId = organizationMapper.selectByPrimaryKey(orgaId).getCreaterId();
        return createrId.equals(userId);
    }

    @Override
    public boolean checkResign(Long userId, Long userOrgaId) {
        UserOrga userOrga = userOrgaMapper.selectByPrimaryKey(userOrgaId);
        if (userOrga != null) {
            Long createrId = organizationMapper.selectByPrimaryKey(userOrga.getOrgaId()).getCreaterId();
            return createrId.equals(userId);
        }
        return false;
    }

    private PageResult<Organization> getOrganizationPageResult(List<Organization> list) {
        PageInfo<Organization> info = new PageInfo<>(list);
        PageResult<Organization> result = new PageResult<>();
        result.setTotal(info.getTotal());
        result.setPageNum(info.getPageNum());
        result.setPages(info.getPages());
        result.setRows(info.getList());
        return result;
    }
}
