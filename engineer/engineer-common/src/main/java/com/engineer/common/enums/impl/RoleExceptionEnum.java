package com.engineer.common.enums.impl;

import com.engineer.common.enums.ExceptionEnum;

/**
 * @author Lemon
 * @date 2020/3/20 15:53
 */
public enum  RoleExceptionEnum implements ExceptionEnum {
    /**
     * code: 状态码
     * message: 异常信息描述
     */
    ROLE_LIST_NOT_FOUND(500, "获取角色列表信息失败"),
    PARAM_ERROR(400, "缺乏参数"),
    DELETE_USERGRP_ROLE_ERROR(500, "取消用户组授权失败"),
    INSERT_USERGRP_ROLE_ERROR(500, "用户组授权失败"),
    UPDATE_ROLE_STATUS_FAILED(500, "更新角色状态失败"),
    DELETE_ROLE_FAILED(500, "删除角色失败"),
    ADD_ROLE_FAILED(500, "添加角色失败"),
    UPDATE_ROLE_FAILED(500, "更新角色失败"),
    DELETE_USER_ROLE_FAILED(500, "取消用户角色授权失败"),
    ADD_USER_ROLE_FAILED(500, "添加用户角色授权失败")
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

    RoleExceptionEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
