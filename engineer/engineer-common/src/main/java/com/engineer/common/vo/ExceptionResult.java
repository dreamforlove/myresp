package com.engineer.common.vo;


import com.engineer.common.enums.ExceptionEnum;
import lombok.Data;

/**
 * @author Lemon
 * @date 2019/8/22 18:42
 */
@Data
public class ExceptionResult {
    private int status;
    private String message;
    private Long timestamp;

    public ExceptionResult(ExceptionEnum em){
        this.status = em.getCode();
        this.message = em.getMessage();
        this.timestamp = System.currentTimeMillis();
    }
}
