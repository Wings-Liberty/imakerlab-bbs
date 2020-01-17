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
    private T body;

    public static ResultUtils success(){

        ResultUtils resultUtils = new ResultUtils();
        resultUtils.setCode(HttpStatus.OK.value());
        resultUtils.setMsg("成功");
        resultUtils.setBody("");

        return resultUtils;
    }

    public static ResultUtils failure(Integer code) {

        ResultUtils resultUtils = new ResultUtils();
        resultUtils.setCode(code);
        resultUtils.setMsg("失败");
        resultUtils.setBody("");

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

    public T getBody() {
        return body;
    }

    public ResultUtils<T> setBody(T body) {
        this.body = body;
        return this;
    }


}
