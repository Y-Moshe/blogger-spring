package com.blogger.bloggerspring.Errors;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ApiError extends RuntimeException {
    private HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

    public ApiError(String message) {
        super(message);
    }

    public ApiError(String message, HttpStatus status) {
        this(message);
        this.status = status;
    }
}
