package com.ecom.productservice.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
@Getter
public enum ErrorKey {
    NOT_FOUND(HttpStatus.NOT_FOUND),
    SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR)

    ;

    private HttpStatus code;
    ErrorKey(HttpStatus httpStatus) {
        this.code = httpStatus;
    }
}
