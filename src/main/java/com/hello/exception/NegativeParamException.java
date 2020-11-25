package com.hello.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class NegativeParamException extends RuntimeException{
    public NegativeParamException(String s) {
        super(s);
    }
}
