package com.engineer.user.controller;

import com.engineer.common.enums.impl.EnabledEnum;
import com.engineer.common.vo.PageResult;
import com.engineer.user.pojo.Menu;
import com.engineer.user.pojo.Resource;
import com.engineer.user.pojo.RolePermission;
import com.engineer.user.service.PermissionService;
import com.engineer.user.vo.MenuVo;
import com.engineer.user.vo.ResourceVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * @author Lemon
 * @date 2020/3/23 19:59
 */
@RestController
@RequestMapping("permission")
public class PermissionController {

    @Autowired
    PermissionService permissionService;

    @GetMapping("role/authorized/menu")
    public ResponseEntity<PageResult<MenuVo>> getPagedAuthorizedMenuList(
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
            @RequestParam(value = "roleId") Long roleId
    ) {
        return ResponseEntity.ok(permissionService.getPagedAuthorizedMenuListByRoleId(pageNum, pageSize, roleId));
    }

    @GetMapping("role/unauthorized/menu")
    public ResponseEntity<PageResult<MenuVo>> getPagedUnauthorizedMenuList(
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
            @RequestParam(value = "roleId") Long roleId
    ) {
        return ResponseEntity.ok(permissionService.getPagedUnauthorizedMenuListByRoleId(pageNum, pageSize, roleId));
    }

    @GetMapping("role/authorized/resource")
    public ResponseEntity<PageResult<ResourceVo>> getPagedAuthorizedResourceList(
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
            @RequestParam(value = "roleId") Long roleId
    ) {
        return ResponseEntity.ok(permissionService.getPagedAuthorizedResourceListByRoleId(pageNum, pageSize, roleId));
    }

    @GetMapping("role/unauthorized/resource")
    public ResponseEntity<PageResult<ResourceVo>> getPagedUnauthorizedResourceList(
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
            @RequestParam(value = "roleId") Long roleId
    ) {
        return ResponseEntity.ok(permissionService.getPagedUnauthorizedResourceListByRoleId(pageNum, pageSize, roleId));
    }

    @PreAuthorize("hasAuthority('/permission/role/authorization')")
    @PostMapping("cancel/authorization")
    public ResponseEntity<Void> cancelAuthorization(
            @RequestParam("roleId") Long roleId,
            @RequestParam("permissionId") Long permissionId
    ) {
        permissionService.cancelAuthorization(roleId, permissionId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PreAuthorize("hasAuthority('/permission/role/authorization')")
    @PostMapping("authorization")
    public ResponseEntity<Void> authorization(@RequestBody RolePermission rolePermission) {
        permissionService.authorization(rolePermission);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("menu/list")
    public ResponseEntity<PageResult<MenuVo>> getMenuList(
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize
    ) {
        return ResponseEntity.ok(permissionService.getPagedMenuList(pageNum, pageSize));
    }

    @GetMapping("resource/list")
    public ResponseEntity<PageResult<ResourceVo>> getResourceList(
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize
    ) {
        return ResponseEntity.ok(permissionService.getPagedResourceList(pageNum, pageSize));
    }

    @PreAuthorize("hasAuthority('/permission/disabled')")
    @PostMapping("disabled")
    public ResponseEntity<Void> setPermissionDisabled(@RequestParam("permissionId") Long permissionId) {
        permissionService.setPermissionStatus(permissionId, EnabledEnum.DISABLED.getValue());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PreAuthorize("hasAuthority('/permission/enabled')")
    @PostMapping("enabled")
    public ResponseEntity<Void> setPermissionEnabled(@RequestParam("permissionId") Long permissionId) {
        permissionService.setPermissionStatus(permissionId, EnabledEnum.ENABLED.getValue());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PreAuthorize("hasAuthority('/permission/delete')")
    @DeleteMapping("{permissionId}")
    public ResponseEntity<Void> deleteRoleById(@PathVariable Long permissionId) {
        permissionService.deletePermissionById(permissionId);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAuthority('/permission/add')")
    @PostMapping("add/menu")
    public ResponseEntity<Void> addMenuPermission(@RequestBody MenuVo menuVo) {
        permissionService.addMenuPermission(menuVo);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PreAuthorize("hasAuthority('/permission/add')")
    @PostMapping("add/resource")
    public ResponseEntity<Void> addResourcePermission(@RequestBody ResourceVo resourceVo) {
        permissionService.addResourcePermission(resourceVo);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PreAuthorize("hasAuthority('/permission/update')")
    @PutMapping("update/menu")
    public ResponseEntity<Void> updateMenuPermission(@RequestBody Menu menu) {
        permissionService.updateMenuPermission(menu);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PreAuthorize("hasAuthority('/permission/update')")
    @PutMapping("update/resource")
    public ResponseEntity<Void> updateResourcePermission(@RequestBody Resource resource) {
        permissionService.updateResourcePermission(resource);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
