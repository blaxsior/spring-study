package com.blaxsior.exhandle.exception.advice;

import com.blaxsior.exhandle.api.ErrorResult;
import com.blaxsior.exhandle.resolver.UserException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice(basePackages = "com.blaxsior.exhandle.api") // API 목적
public class ExControllerAdvice {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public ErrorResult handleIllegalArgsEx(IllegalArgumentException e) {
        var errorResult = new ErrorResult(400, e.getMessage());
        return errorResult;
    }

    @ExceptionHandler(UserException.class)
    public ResponseEntity<ErrorResult> handleUserEx(UserException e) {
        var errorResult = new ErrorResult(400, e.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorResult);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ErrorResult handleEx(Exception e) {
        return new ErrorResult(500, "Internal Server Error");
    }
}
