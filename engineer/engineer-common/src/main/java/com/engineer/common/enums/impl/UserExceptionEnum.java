package com.engineer.common.enums.impl;

import com.engineer.common.enums.ExceptionEnum;

/**
 * 用户模块异常信息枚举类
 * @author Lemon
 * @date 2020/3/10 15:51
 */
public enum UserExceptionEnum implements ExceptionEnum {

    /**
     * code: 状态码
     * message: 异常信息描述
     */
    PARAM_ERROR(400, "参数错误"),
    USER_LIST_NOT_FOUND(404, "用户信息列表未找到"),
    UPDATE_USER_STATUS_FAIL(500, "更新用户状态失败"),
    USER_NOT_FOUND(404, "未找到用户信息"),
    UPDATE_USER_ERROR(500, "修改用户信息失败"),
    INVALID_CODE(400, "验证码不存在或已经过期"),
    REGISTER_USER_FAILED(500, "注册新用户失败"),
    CHANGE_PASSWORD_FAILED(500, "修改密码失败")
    ;
    private int code;
    private String message;


    @Override
    public int getCode() {
        return 0;
    }

    @Override
    public String getMessage() {
        return null;
    }

    UserExceptionEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
