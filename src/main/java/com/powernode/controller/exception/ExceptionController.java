package com.powernode.controller.exception;

import com.powernode.commns.ResponseObject;
import com.powernode.exception.CRMException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 控制层异常处理
 */
@ControllerAdvice
public class ExceptionController {

    /**
     * 全局异常处理
     * @param e 发生的异常对象
     * @return
     */
    @ExceptionHandler
    @ResponseBody
    public ResponseObject handleException(Exception e){
        ResponseObject ro = new ResponseObject();
        ro.setStateCode(500);
        if (e instanceof CRMException){
            ro.setMsg(e.getMessage());
        }else {
            e.printStackTrace();
            ro.setMsg("系统正在升级");
        }
        return ro;
    }
}
