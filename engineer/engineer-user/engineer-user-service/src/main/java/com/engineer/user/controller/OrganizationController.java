package com.engineer.user.controller;

import com.engineer.common.enums.impl.EnabledEnum;
import com.engineer.common.enums.impl.VerifyEnum;
import com.engineer.common.enums.impl.WorkingStatusEnum;
import com.engineer.common.vo.PageResult;
import com.engineer.common.vo.UserInfo;
import com.engineer.user.interceptor.UserInfoInterceptor;
import com.engineer.user.pojo.Organization;
import com.engineer.user.service.OrganizationService;
import com.engineer.user.vo.InvitedStaff;
import com.engineer.user.vo.UserOrgaVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Lemon
 * @date 2020/3/14 20:41
 */
@RestController
@RequestMapping("orga")
public class OrganizationController {

    @Autowired
    private OrganizationService organizationService;

    @GetMapping("list")
    public ResponseEntity<PageResult<Organization>> getOrgaList(
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
            @RequestParam(value = "keywords", required = false) String keywords,
            @RequestParam(value = "userId", required = false) Long userId
    ) {
        PageResult<Organization> result = organizationService.getOrgaListByKeywords(pageNum, pageSize, keywords, userId);
        return ResponseEntity.ok(result);
    }

    @GetMapping("management/list")
    public ResponseEntity<PageResult<Organization>> getOrgaManagementList(
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
            @RequestParam(value = "verify", required = false) String verify,
            @RequestParam(value = "keywordsType", required = false) String keywordsType,
            @RequestParam(value = "keywords", required = false) String keywords,
            @RequestParam(value = "userId", required = false) Long userId
    ) {
        PageResult<Organization> result = organizationService.getPagedOrgaManagementList(pageNum, pageSize, verify, keywordsType, keywords, userId);
        return ResponseEntity.ok(result);
    }

    @PreAuthorize("hasAuthority('/orga/disabled')")
    @PostMapping("disabled")
    public ResponseEntity<Void> setOrgaDisabled(@RequestParam("orgaId") Long orgaId) {
        organizationService.setOrgaStatus(orgaId, EnabledEnum.DISABLED.getValue());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PreAuthorize("hasAuthority('/orga/enabled')")
    @PostMapping("enabled")
    public ResponseEntity<Void> setOrgaEnabled(@RequestParam("orgaId") Long orgaId) {
        organizationService.setOrgaStatus(orgaId, EnabledEnum.ENABLED.getValue());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PreAuthorize("hasAuthority('/orga/verify')")
    @PostMapping("adopt")
    public ResponseEntity<Void> adoptOrga(@RequestParam("orgaId") Long orgaId) {
        organizationService.setOrgaVerify(orgaId, VerifyEnum.ADOPTED.getValue());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PreAuthorize("hasAuthority('/orga/verify')")
    @PostMapping("refuse")
    public ResponseEntity<Void> refuseOrga(@RequestParam("orgaId") Long orgaId) {
        organizationService.setOrgaVerify(orgaId, VerifyEnum.REFUSED.getValue());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("update")
    public ResponseEntity<Void> updateOrga(@RequestBody Organization organization) {
        UserInfo userInfo = UserInfoInterceptor.getUserInfo();
        if (!userInfo.getAuthorities().contains("/orga/update")
                && !organizationService.checkUserIsOrgaCreater(userInfo.getUserId(), organization.getOrgaId())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        organizationService.updateOrga(organization);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("created/{userId}")
    public ResponseEntity<List<Organization>> getCreatedOrgaByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(organizationService.getCreatedOrgaByUserId(userId));
    }

    @GetMapping("joined/{userId}")
    public ResponseEntity<List<Organization>> getJoinedOrgaByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(organizationService.getJoinedOrgaByUserId(userId));
    }

    @PostMapping("add")
    public ResponseEntity<Void> addOrga(@RequestBody Organization organization) {
        organizationService.addOrga(organization);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("delete/{orgaId}")
    public ResponseEntity<Void> delOrga(@PathVariable Long orgaId) {
        UserInfo userInfo = UserInfoInterceptor.getUserInfo();
        if (!organizationService.checkUserIsOrgaCreater(userInfo.getUserId(), orgaId)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        organizationService.delOrga(orgaId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("invited/list")
    public ResponseEntity<PageResult<UserOrgaVo>> getInvitedListByUserId(
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
            @RequestParam("userId") Long userId
    ) {
        return ResponseEntity.ok(organizationService.getInvitedListByUserId(pageNum, pageSize, userId));
    }

    @PostMapping("invited/accept")
    public ResponseEntity<Void> acceptOrgaInvited(
            @RequestParam("userOrgaId") Long userOrgaId
    ) {
        organizationService.setUserOrgaStatus(userOrgaId, WorkingStatusEnum.ACCEPTED.getValue());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("invited/refuse")
    public ResponseEntity<Void> refuseOrgaInvited(
            @RequestParam("userOrgaId") Long userOrgaId
    ) {
        organizationService.setUserOrgaStatus(userOrgaId, WorkingStatusEnum.REFUSED.getValue());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("resign")
    public ResponseEntity<Void> resign(
            @RequestParam("userOrgaId") Long userOrgaId
    ) {
        UserInfo userInfo = UserInfoInterceptor.getUserInfo();
        if (!organizationService.checkResign(userInfo.getUserId(), userOrgaId)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        organizationService.setUserOrgaStatus(userOrgaId, WorkingStatusEnum.RESIGNED.getValue());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("staff/list")
    public ResponseEntity<PageResult<InvitedStaff>> getInvitedStaffList(
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
            @RequestParam(value = "orgaId") Long orgaId,
            @RequestParam(value = "keywordsType", required = false) String keywordsType,
            @RequestParam(value = "keywords", required = false) String keywords
    ) {
        PageResult<InvitedStaff> userList = organizationService.getStaffList(pageNum, pageSize, orgaId, keywordsType, keywords);
        return ResponseEntity.ok(userList);
    }

    @PostMapping("invite")
    public ResponseEntity<Void> invite(
            @RequestParam("orgaId") Long orgaId,
            @RequestParam("userId") Long userId
    ) {
        UserInfo userInfo = UserInfoInterceptor.getUserInfo();
        if (!organizationService.checkUserIsOrgaCreater(userInfo.getUserId(), orgaId)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        organizationService.inviteStaff(orgaId, userId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
