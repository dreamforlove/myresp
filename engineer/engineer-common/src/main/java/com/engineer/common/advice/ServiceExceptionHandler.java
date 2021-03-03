package com.engineer.common.advice;

import com.engineer.common.enums.ExceptionEnum;
import com.engineer.common.exceptions.ServiceException;
import com.engineer.common.vo.ExceptionResult;
import com.google.common.collect.ImmutableMap;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Lemon
 * @date 2019/9/9 14:47
 */
@ControllerAdvice
public class ServiceExceptionHandler {

    /**
     * 定义map，配置异常类型所对应的错误代码
     */
    private static ImmutableMap<Class<? extends Throwable>,ExceptionResult> EXCEPTIONS;
    /**
     * 定义map的builder对象，去构建ImmutableMap
     */
    protected static ImmutableMap.Builder<Class<? extends Throwable>, ExceptionResult> builder = ImmutableMap.builder();

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<ExceptionResult> handleException(ServiceException e){
        ExceptionEnum enums = e.getExceptionEnum();
        return ResponseEntity.status(enums.getCode()).body(new ExceptionResult(enums));
    }

    /**
     * 捕获Exception此类异常
     * @param exception
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<ExceptionResult> exception(Exception exception){
        if(EXCEPTIONS == null){
            // EXCEPTIONS构建成功
            EXCEPTIONS = builder.build();
        }
        //从EXCEPTIONS中找异常类型所对应的错误代码，如果找到了将错误代码响应给用户，如果找不到给用户响应99999异常
        ExceptionResult resultCode = EXCEPTIONS.get(exception.getClass());
        return ResponseEntity.status(resultCode.getStatus()).body(resultCode);
//        if(resultCode !=null){
//            return new ResponseResult(resultCode);
//        }else{
//            //返回99999异常
//            return new ResponseResult(CommonCode.SERVER_ERROR);
//        }


    }
}
