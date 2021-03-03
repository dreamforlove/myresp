package com.engineer.user.controller;

import com.engineer.common.enums.impl.EnabledEnum;
import com.engineer.common.enums.impl.VerifyEnum;
import com.engineer.user.interceptor.UserInfoInterceptor;
import com.engineer.common.vo.PageResult;
import com.engineer.common.vo.UserInfo;
import com.engineer.user.pojo.UserUsergrp;
import com.engineer.user.pojo.Usergrp;
import com.engineer.user.service.UsergrpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * @author Lemon
 * @date 2020/3/16 15:46
 */
@RestController
@RequestMapping("usergrp")
public class UsergrpController {

    @Autowired
    private UsergrpService usergrpService;

    @GetMapping("list")
    public ResponseEntity<PageResult<Usergrp>> getUsergrpListPageByOrgaId(
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
            @RequestParam(value = "orgaId") Long orgaId
    ) {
        PageResult<Usergrp> result = usergrpService.getUsergrpListPageByOrgaId(pageNum, pageSize, orgaId);
        return ResponseEntity.ok(result);
    }

    @PreAuthorize("hasAuthority('/usergrp/disabled')")
    @PostMapping("disabled")
    public ResponseEntity<Void> setUsergrpDisabled(@RequestParam("usergrpId") Long usergrpId) {
        usergrpService.setUsergrpStatus(usergrpId, EnabledEnum.DISABLED.getValue());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PreAuthorize("hasAuthority('/usergrp/enabled')")
    @PostMapping("enabled")
    public ResponseEntity<Void> setUsergrpEnabled(@RequestParam("usergrpId") Long usergrpId) {
        usergrpService.setUsergrpStatus(usergrpId, EnabledEnum.ENABLED.getValue());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PreAuthorize("hasAuthority('/usergrp/verify')")
    @PostMapping("adopt")
    public ResponseEntity<Void> adoptUsergrp(@RequestParam("usergrpId") Long usergrpId) {
        usergrpService.setUsergrpVerify(usergrpId, VerifyEnum.ADOPTED.getValue());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PreAuthorize("hasAuthority('/usergrp/verify')")
    @PostMapping("refuse")
    public ResponseEntity<Void> refuseUsergrp(@RequestParam("usergrpId") Long usergrpId) {
        usergrpService.setUsergrpVerify(usergrpId, VerifyEnum.REFUSED.getValue());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PreAuthorize("hasAuthority('/usergrp/add')")
    @PostMapping("add")
    public ResponseEntity<Void> addUsergrp(@RequestBody Usergrp usergrp) {
        usergrpService.addUsergrp(usergrp);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PreAuthorize("hasAuthority('/usergrp/update')")
    @PutMapping("update")
    public ResponseEntity<Void> updateUsergrp(@RequestBody Usergrp usergrp) {
        usergrpService.updateUsergrp(usergrp);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PreAuthorize("hasAuthority('/usergrp/delete')")
    @DeleteMapping("{usergrpId}")
    public ResponseEntity<Void> deleteUsergrpById(@PathVariable Long usergrpId) {
        usergrpService.deleteUsergrpById(usergrpId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("auth/status")
    public ResponseEntity<Boolean> getUsergrpAuthorizationStatus(
            @RequestParam("usergrpId") Long usergrpId,
            @RequestParam("userId") Long userId
    ) {
        return ResponseEntity.ok(usergrpService.getUsergrpAuthorizationStatus(usergrpId, userId));
    }

    @PostMapping("cancel/authorization")
    public ResponseEntity<Void> cancelAuthorization(
            @RequestParam("orgaId") Long orgaId,
            @RequestParam("usergrpId") Long usergrpId,
            @RequestParam("userId") Long userId
    ) {
        UserUsergrp userUsergrp = new UserUsergrp();
        userUsergrp.setProductid(orgaId);
        userUsergrp.setUsergrpId(usergrpId);
        userUsergrp.setUserId(userId);
        UserInfo userInfo = UserInfoInterceptor.getUserInfo();
        if (!userInfo.getAuthorities().contains("/usergrp/user/authorization")
                && !usergrpService.checkUsergrpUserAuthorization(userInfo.getUserId(), userUsergrp)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        usergrpService.cancelAuthorization(orgaId, usergrpId, userId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("authorization")
    public ResponseEntity<Void> authorization(@RequestBody UserUsergrp userUsergrp) {
        UserInfo userInfo = UserInfoInterceptor.getUserInfo();
        if (!userInfo.getAuthorities().contains("/usergrp/user/authorization")
                && !usergrpService.checkUsergrpUserAuthorization(userInfo.getUserId(), userUsergrp)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        usergrpService.authorization(userUsergrp);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
