package com.ssafy.tipsy.common.exception;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(String source,String something) {
        super(source+"에서 " + something + "을 찾을 수 없습니다.");
    }
}
