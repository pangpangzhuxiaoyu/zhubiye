package com.zhu.controller;

/**
 * 前后端返回结果类
 */
public class Result {
    //存放后端返回的数据
    private Object data;
    //存放正确/错误代码
    private Integer code;
    //存放错误信息
    private String msg;

    public Result() {
    }
    public Result (Integer code){
        this.code=code;
    }
    public Result( Integer code,Object data) {
        this.data = data;
        this.code = code;
    }

    public Result(Integer code, String msg,Object data ) {
        this.data = data;
        this.code = code;
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
