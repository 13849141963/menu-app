package com.zy.cn.controller.util;
/*****
 * MVC控制器返回通用对象
 * @param <T>
 */
public class ResultUtil<T> {

    /*错误码*/
    private Integer code;
    /*提示信息*/
    private String msg;
    /*具体的内容*/
    private T data;
    //是否成功
    private Boolean status;

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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
