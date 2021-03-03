package com.engineer.common.enums.impl;

import com.engineer.common.enums.ExceptionEnum;

/**
 * @author Lemon
 * @date 2020/3/14 21:06
 */
public enum OrgaExceptionEnum implements ExceptionEnum {

    /**
     * code: 状态码
     * message: 异常信息描述
     */
    PARAM_ERROR(400, "参数有误"),
    ORGA_NOT_FOUND(404, "组织机构未找到"),
    UPDATE_ORGA_STATUS_FAILED(500, "更新组织机构状态失败"),
    UPDATE_ORGA_VERIFY_FAILED(500, "更新组织机构审核状态失败"),
    UPDATE_ORGA_FAILED(500, "更新组织机构信息失败"),
    ADD_ORGA_FAILED(500, "认证新组织机构失败"),
    DELETE_ORGA_FAILED(500, "删除组织机构失败"),
    UPDATE_USER_ORGA_STATUS_FAILED(500, "更新入职状态失败"),
    INSERT_USER_ORGA_FAILED(500, "邀请新员工失败"),
    USER_ORGA_IS_EXISTS(400, "员工已存在")
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

    OrgaExceptionEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
