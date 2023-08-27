package com.blogger.bloggerspring.Errors;

import java.util.List;
import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiErrorResponse {
    private HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
    private int statusCode = HttpStatus.INTERNAL_SERVER_ERROR.value();
    private String message;
    private List<String> errors;

    public ApiErrorResponse(String message) {
        this.message = message;
    }

    public ApiErrorResponse(String message, HttpStatus status) {
        this(message);
        this.status = status;
        this.statusCode = status.value();
    }
}
