package com.example.taskmanagementapplication.controller;

import com.example.taskmanagementapplication.domain.response.ResponseError;
import com.example.taskmanagementapplication.exception.CustomException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@Log4j2
@RestControllerAdvice
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(CustomException.class)
  protected ResponseEntity<ResponseError> customException(
      CustomException exception,
      HttpServletRequest request
  ) {
    log.info(exception.toString());
    log.error("Fail: {}", exception.getMessage(), exception);
    return ResponseEntity
        .status(exception.getStatus())
        .body(ResponseError.builder()
            .path(request.getRequestURI())
            .timestamp(exception.getTimestamp())
            .status(exception.getStatus().value())
            .error(exception.getError())
            .message(exception.getMessage())
            .build());
  }

  @ExceptionHandler(ResponseStatusException.class)
  protected ResponseEntity<?> handleResponseStatusException(ResponseStatusException exception) {
    log.info(exception.getClass().getName());
    log.error("Fail: {}", exception.getMessage(), exception);
    throw new ResponseStatusException(exception.getStatus(), exception.getReason());
  }

}
