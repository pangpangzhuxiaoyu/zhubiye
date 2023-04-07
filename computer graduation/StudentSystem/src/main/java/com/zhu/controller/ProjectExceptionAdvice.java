package com.zhu.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice//异常处理
public class ProjectExceptionAdvice {
    @ExceptionHandler(Exception.class)//拦截异常
    public void doException(Exception ex){

    }
}
