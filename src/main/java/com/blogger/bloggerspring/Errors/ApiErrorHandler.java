package com.blogger.bloggerspring.Errors;

import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiErrorHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorResponse> handleApiValidation(
            MethodArgumentNotValidException ex) {

        List<String> errors = new ArrayList<>();
        ex
                .getBindingResult()
                .getAllErrors().forEach((error) -> {
                    String fieldName = ((FieldError) error).getField();
                    String errorMessage = error.getDefaultMessage();
                    errors.add(fieldName + " " + errorMessage);
                });

        String firstErrMsg = errors.get(0);
        ApiErrorResponse error = new ApiErrorResponse(firstErrMsg, HttpStatus.BAD_REQUEST);
        error.setErrors(errors);
        return new ResponseEntity<ApiErrorResponse>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ApiErrorResponse> handleAuthenticationError(AuthenticationException ex) {
        ApiErrorResponse error = new ApiErrorResponse(ex.getMessage(), HttpStatus.UNAUTHORIZED);
        return new ResponseEntity<ApiErrorResponse>(error, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(ApiError.class)
    public ResponseEntity<ApiErrorResponse> handleApiError(ApiError ex) {
        ApiErrorResponse error = new ApiErrorResponse(ex.getMessage(), ex.getStatus());
        return new ResponseEntity<ApiErrorResponse>(error, ex.getStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponse> handleAnyError(Exception ex) {
        ApiErrorResponse error = new ApiErrorResponse(ex.getMessage());
        System.out.println(ex);
        return new ResponseEntity<ApiErrorResponse>(error, error.getStatus());
    }
}
