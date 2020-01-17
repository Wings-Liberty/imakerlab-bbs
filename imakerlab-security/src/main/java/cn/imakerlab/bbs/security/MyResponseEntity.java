package cn.imakerlab.bbs.security;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * 自定义返回结果集
 */
public class MyResponseEntity<P> extends ResponseEntity {

    private int code;

    private String msg;

    private P body;

    public MyResponseEntity() {
        super(HttpStatus.OK);
    }

    public int getCode() {
        return code;
    }

    public MyResponseEntity setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public MyResponseEntity setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public P getBody() {
        return body;
    }

    public MyResponseEntity<P> setBody(P body) {
        this.body = body;
        return this;
    }

}