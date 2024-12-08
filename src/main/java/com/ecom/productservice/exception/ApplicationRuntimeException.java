package com.ecom.productservice.exception;

public class ApplicationRuntimeException extends BaseRuntimeException{

    public ApplicationRuntimeException(ApiExceptionType apiExceptionType) {
        super(apiExceptionType);
    }
}
