package com.andrew.messenger.http.handlers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice(basePackages = "com.andrew.messenger.http.rest")
public class RestControllerExceptionHandler extends ResponseEntityExceptionHandler {
   // @ExceptionHandler(Exception.class)
   // public ResponseEntity<Object> handleException(Exception exc) {
   //     return ResponseEntity.badRequest().body(exc.getMessage());
   // }
}
