package com.ecom.productservice.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ApiExceptionType {

    SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR,"server_error" ),
    NOT_FOUND(HttpStatus.NOT_FOUND,"generic_not_found" );


    final HttpStatus httpStatus;
    final String messageCode;

    ApiExceptionType(HttpStatus httpStatus, String messageCode) {
        this.httpStatus = httpStatus;
        this.messageCode = messageCode;
    }


}
