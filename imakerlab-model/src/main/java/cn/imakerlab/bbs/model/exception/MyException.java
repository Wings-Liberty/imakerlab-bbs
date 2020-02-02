package cn.imakerlab.bbs.model.exception;

public class MyException extends RuntimeException {

    String msg;

    public MyException() {
    }

    public MyException(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
