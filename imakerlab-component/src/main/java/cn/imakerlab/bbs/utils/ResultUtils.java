package cn.imakerlab.bbs.utils;

import org.springframework.http.HttpStatus;

/**
 * 统一返回结果集
 * @param <T>
 */
public class ResultUtils<T> {

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 返回的字符串信息
     */
    private String msg;

    /**
     * 返回的对象
     */
    private T data;

    public static ResultUtils success(){

        ResultUtils resultUtils = new ResultUtils();
        resultUtils.setCode(HttpStatus.OK.value());
        resultUtils.setMsg("成功");
        resultUtils.setData("");

        return resultUtils;
    }

    public static ResultUtils success(Object data){

        ResultUtils resultUtils = new ResultUtils();
        resultUtils.setCode(HttpStatus.OK.value());
        resultUtils.setMsg("成功");
        resultUtils.setData(data);

        return resultUtils;
    }

    public static ResultUtils failure(Integer code) {

        ResultUtils resultUtils = new ResultUtils();
        resultUtils.setCode(code);
        resultUtils.setMsg("失败");
        resultUtils.setData("");

        return resultUtils;
    }

    public Integer getCode() {
        return code;
    }

    public ResultUtils<T> setCode(Integer code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public ResultUtils<T> setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public T getData() {
        return data;
    }

    public ResultUtils<T> setData(T data) {
        this.data = data;
        return this;
    }
}