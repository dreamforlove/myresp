package com.engineer.common.enums.impl;

import com.engineer.common.enums.ExceptionEnum;

/**
 * 授权模块异常枚举类
 * @author Lemon
 * @date 2019/9/9 14:34
 */
public enum AuthExceptionEnum implements ExceptionEnum {
    /**
     * code: 状态码
     * message: 异常信息描述
     */
    AUTH_LOGIN_PARAM_WRONG(400, "登录参数有误"),
    AUTH_LOGIN_APPLYTOKEN_FAIL(500, "申请令牌失败"),
    AUTH_LOGIN_TOKEN_SAVEFAIL(500, "存储令牌失败"),
    AUTH_CREDENTIAL_ERROR(400, "用户名或密码错误"),
    AUTH_GET_USER_INFO_ERROR(500, "获取用户信息失败"),
    AUTH_LOGIN_AUTH_SERVER_NOTFOUND(500, "获取认证服务实例失败"),
    AUTH_USER_DISABLED(403, "用户已被禁用")
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

    AuthExceptionEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
