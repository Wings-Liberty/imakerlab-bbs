package cn.imakerlab.bbs.web.handler;

import cn.imakerlab.bbs.model.exception.MyException;
import cn.imakerlab.bbs.utils.ResultUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class MyExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @ResponseBody
    @ExceptionHandler(value = MyException.class)
    public ResultUtils myExceptionHandler(MyException e){
        logger.info("MyException");

        return ResultUtils.failure(100);
    }

    @ResponseBody
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResultUtils MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e){
        logger.info("接收的参数出现异常");

        String msg;

        msg = ((MethodArgumentNotValidException) e).getBindingResult().getFieldError().getDefaultMessage();

        return ResultUtils.failure(100);
    }

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public ResultUtils Exception(Exception e){

        logger.info("Exception");

        String msg = e.getMessage();

        return ResultUtils.failure(100).setMsg(msg);

    }

}
