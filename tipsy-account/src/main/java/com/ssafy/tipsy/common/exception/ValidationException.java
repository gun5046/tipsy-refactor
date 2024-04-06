package com.ssafy.tipsy.common.exception;

public class ValidationException extends RuntimeException {
    public ValidationException(String source,String something) {
        super(source+"에서 " + something + "는(은) 사용할 수 없습니다.");
    }
}
