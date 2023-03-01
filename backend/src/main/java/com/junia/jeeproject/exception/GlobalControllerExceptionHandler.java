package com.junia.jeeproject.exception;

import com.junia.jeeproject.dto.ExceptionResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalControllerExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(HttpException.class)
  public ResponseEntity<ExceptionResponse> handleHttpException(HttpException e, HttpServletRequest request){
    return ResponseEntity.status(e.getStatus())
        .body(ExceptionResponse.builder()
            .status(e.getStatus())
            .message(e.getMessage())
            .url(request.getRequestURI())
            .details(e.getDetails())
            .build());
  }
}
