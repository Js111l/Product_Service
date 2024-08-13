package com.ecom.productservice.exception;

import lombok.Getter;

@Getter
public class LogicalException extends RuntimeException {

    private ErrorKey key;
    public LogicalException(ErrorKey key) {
        this.key = key;
    }
}
