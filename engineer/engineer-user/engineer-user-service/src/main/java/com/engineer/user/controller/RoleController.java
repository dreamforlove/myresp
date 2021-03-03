package com.engineer.user.controller;

import com.engineer.common.enums.impl.EnabledEnum;
import com.engineer.user.interceptor.UserInfoInterceptor;
import com.engineer.common.vo.PageResult;
import com.engineer.common.vo.UserInfo;
import com.engineer.user.pojo.Role;
import com.engineer.user.pojo.UserRole;
import com.engineer.user.pojo.UsergrpRole;
import com.engineer.user.service.RoleService;
import com.engineer.user.vo.UserRoleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * @author Lemon
 * @date 2020/3/20 15:06
 */
@RestController
@RequestMapping("role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping("usergrp/authorized")
    public ResponseEntity<PageResult<Role>> getPagedAuthorizedRoleList(
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
            @RequestParam(value = "usergrpId") Long usergrpId
    ) {
        return ResponseEntity.ok(roleService.getPagedAuthorizedRoleListByUsergrpId(pageNum, pageSize, usergrpId));
    }

    @GetMapping("usergrp/unauthorized")
    public ResponseEntity<PageResult<Role>> getPagedUnauthorizedRoleList(
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
            @RequestParam(value = "usergrpId") Long usergrpId
    ) {
        return ResponseEntity.ok(roleService.getPagedUnauthorizedRoleListByUsergrpId(pageNum, pageSize, usergrpId));
    }

    @PreAuthorize("hasAuthority('/role/usergrp/authorization')")
    @PostMapping("cancel/authorization")
    public ResponseEntity<Void> cancelAuthorization(
            @RequestParam("usergrpId") Long usergrpId,
            @RequestParam("roleId") Long roleId
    ) {
        roleService.cancelAuthorization(usergrpId, roleId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PreAuthorize("hasAuthority('/role/usergrp/authorization')")
    @PostMapping("authorization")
    public ResponseEntity<Void> authorization(@RequestBody UsergrpRole usergrpRole) {
        roleService.authorization(usergrpRole);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("list")
    public ResponseEntity<PageResult<Role>> getRoleList(
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
            @RequestParam(value = "keywordsType", required = false) String keywordsType,
            @RequestParam(value = "keywords", required = false) String keywords
    ) {
        return ResponseEntity.ok(roleService.getRoleList(pageNum, pageSize, keywordsType, keywords));
    }

    @PreAuthorize("hasAuthority('/role/disabled')")
    @PostMapping("disabled")
    public ResponseEntity<Void> setRoleDisabled(@RequestParam("roleId") Long roleId) {
        roleService.setRoleStatus(roleId, EnabledEnum.DISABLED.getValue());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PreAuthorize("hasAuthority('/role/enabled')")
    @PostMapping("enabled")
    public ResponseEntity<Void> setRoleEnabled(@RequestParam("roleId") Long roleId) {
        roleService.setRoleStatus(roleId, EnabledEnum.ENABLED.getValue());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PreAuthorize("hasAuthority('/role/delete')")
    @DeleteMapping("{roleId}")
    public ResponseEntity<Void> deleteRoleById(@PathVariable Long roleId) {
        roleService.deleteRoleById(roleId);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAuthority('/role/add')")
    @PostMapping("add")
    public ResponseEntity<Void> addRole(@RequestBody Role role) {
        roleService.addRole(role);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PreAuthorize("hasAuthority('/role/update')")
    @PutMapping("update")
    public ResponseEntity<Void> updateRole(@RequestBody Role role) {
        roleService.updateRole(role);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("usergrp/user/list")
    public ResponseEntity<PageResult<UserRoleVo>> getPagedUserRoleListFromUsergrp(
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
            @RequestParam(value = "usergrpId") Long usergrpId,
            @RequestParam(value = "userId") Long userId

    ) {
        return ResponseEntity.ok(roleService.getPagedUserRoleListFromUsergrp(pageNum, pageSize, usergrpId, userId));
    }

    @PostMapping("cancel/user/authorization")
    public ResponseEntity<Void> cancelUserAuthorization(
            @RequestParam("orgaId") Long orgaId,
            @RequestParam("roleId") Long roleId,
            @RequestParam("userId") Long userId
    ) {
        UserRole userRole = new UserRole();
        userRole.setProductid(orgaId);
        userRole.setRoleId(roleId);
        userRole.setUserId(userId);
        UserInfo userInfo = UserInfoInterceptor.getUserInfo();
        if (!userInfo.getAuthorities().contains("/role/user/authorization")
                && !roleService.checkRoleUserAuthorization(userInfo.getUserId(), userRole)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        roleService.cancelUserAuthorization(orgaId, roleId, userId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("user/authorization")
    public ResponseEntity<Void> userAuthorization(@RequestBody UserRole userRole) {
        UserInfo userInfo = UserInfoInterceptor.getUserInfo();
        if (!userInfo.getAuthorities().contains("/role/user/authorization")
                && !roleService.checkRoleUserAuthorization(userInfo.getUserId(), userRole)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        roleService.userAuthorization(userRole);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
