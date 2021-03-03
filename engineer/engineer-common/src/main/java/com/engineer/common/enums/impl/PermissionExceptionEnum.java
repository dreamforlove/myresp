package com.engineer.common.enums.impl;

import com.engineer.common.enums.ExceptionEnum;

/**
 * @author Lemon
 * @date 2020/3/24 15:03
 */
public enum PermissionExceptionEnum implements ExceptionEnum {
    /**
     * code: 状态码
     * message: 异常信息描述
     */
    PARAM_ERROR(400, "缺乏参数"),
    DELETE_ROLE_PERMISSION_FAILED(500, "取消角色授权失败"),
    INSERT_ROLE_PERMISSION_FAILED(500, "角色授权失败"),
    UPDATE_PERMISSION_STATUS_FAILED(500, "更新权限状态失败"),
    PERMISSION_NOT_FOUND(404, "权限未找到"),
    DELETE_MENU_PERMISSION_FAILED(500, "删除菜单权限失败"),
    DELETE_RESOURCE_PERMISSION_FAILED(500, "删除功能权限失败"),
    DELETE_PERMISSION_FAILED(500, "删除权限失败"),
    ADD_PERMISSION_FAILED(500, "新增权限失败"),
    ADD_MENU_PERMISSION_FAILED(500, "新增菜单权限失败"),
    ADD_RESOURCE_PERMISSION_FAILED(500, "新增功能权限失败"),
    UPDATE_MENU_PERMISSION_FAILED(500, "更新菜单权限失败"),
    UPDATE_RESOURCE_PERMISSION_FAILED(500, "更新功能权限失败")
    ;
    private int code;
    private String message;

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    PermissionExceptionEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
