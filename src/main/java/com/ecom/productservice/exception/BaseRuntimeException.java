package com.ecom.productservice.exception;

import lombok.Getter;

@Getter
public abstract class BaseRuntimeException extends RuntimeException {
    private final ApiExceptionType apiExceptionType;

    public BaseRuntimeException(ApiExceptionType apiExceptionType) {
        this.apiExceptionType = apiExceptionType;
    }
}
