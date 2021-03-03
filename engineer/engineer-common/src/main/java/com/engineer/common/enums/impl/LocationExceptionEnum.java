package com.engineer.common.enums.impl;

import com.engineer.common.enums.ExceptionEnum;

/**
 * @author Lemon
 * @date 2020/3/29 21:16
 */
public enum LocationExceptionEnum implements ExceptionEnum {
    /**
     * code: 状态码
     * message: 异常信息描述
     */
    PARAM_ERROR(400, "缺乏参数"),
    DELETE_LOCATION_FAILED(500, "删除地区失败"),
    PID_IS_ERROR(400, "父地区编号错误"),
    ADD_LOCATION_FAILED(500, "添加地区失败"),
    UPDATE_LOCATION_FAILED(500, "修改地区信息失败")
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

    LocationExceptionEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
