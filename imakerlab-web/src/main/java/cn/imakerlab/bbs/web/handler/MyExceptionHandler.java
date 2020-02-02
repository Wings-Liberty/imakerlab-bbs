package cn.imakerlab.bbs.web.handler;

import cn.imakerlab.bbs.model.exception.MyException;
import cn.imakerlab.bbs.utils.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@ControllerAdvice
public class MyExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = MyException.class)
    public ResultUtils myExceptionHandler(MyException e){
        return ResultUtils.failure(100).setMsg(e.getMsg());
    }

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public ResultUtils myErrorHandler(Exception e) {
        String msg;
        if(e instanceof BindException){
            msg = ((BindException) e).getBindingResult().getFieldError().getDefaultMessage();
        }else {
            msg = e.getMessage();
        }

        log.info(msg);

        return ResultUtils.failure(100).setMsg(msg);
    }

}