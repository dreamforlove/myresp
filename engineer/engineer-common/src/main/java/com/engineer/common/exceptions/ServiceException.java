package com.engineer.common.exceptions;

import com.engineer.common.enums.ExceptionEnum;

/**
 * @author Lemon
 * @date 2019/9/9 14:37
 */
public class ServiceException extends RuntimeException {

    private ExceptionEnum exceptionEnum;

    public ExceptionEnum getExceptionEnum() {
        return exceptionEnum;
    }

    public ServiceException(ExceptionEnum exceptionEnum) {
        this.exceptionEnum = exceptionEnum;
    }

    public ServiceException() {
    }
}
