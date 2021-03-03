package com.engineer.common.enums.impl;

import com.engineer.common.enums.ExceptionEnum;

/**
 * @author Lemon
 * @date 2020/3/16 16:17
 */
public enum UsergrpExceptionEnum implements ExceptionEnum {

    /**
     * code: 状态码
     * message: 异常信息描述
     */
    PARAM_ERROR(400, "参数有误"),
    USERGRP_LIST_NOT_FOUND(404, "未找到用户组信息"),
    UPDATE_USERGRP_STATUS_FAIL(500, "更新用户组状态失败"),
    UPDATE_USERGRP_VERIFY_FAIL(500, "更新用户组审核状态失败"),
    ADD_USERGRP_FAIL(500, "新增用户组失败"),
    ADD_USERGRP_PARAM_ERROR(500, "缺少用户组参数"),
    UPDATE_USERGRP_FAIL(500, "修改用户组失败"),
    DELETE_USERGRP_FAIL(500, "删除用户组失败"),
    AUTHORIZE_USERGRP_FAILED(500, "授权用户组失败"),
    CANCEL_AUTHORIZE_USERGRP_FAILED(500, "取消用户组授权失败")
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

    UsergrpExceptionEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
