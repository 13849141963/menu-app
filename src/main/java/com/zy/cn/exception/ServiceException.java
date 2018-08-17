package com.zy.cn.exception;

public class ServiceException extends RuntimeException {

    private String code;//状态码

    public ServiceException(String message){

        super(message);

    }



}
