package com.engineer.common.enums;

/**
 * 统一异常处理枚举接口
 * @author Lemon
 * @date 2019/9/9 14:31
 */
public interface ExceptionEnum {
    /**
     * 状态码
     * @return
     */
    int getCode();

    /**
     * 描述
     * @return
     */
    String getMessage();
}
