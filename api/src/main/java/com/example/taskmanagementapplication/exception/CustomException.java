package com.example.taskmanagementapplication.exception;

import com.example.taskmanagementapplication.enumeration.ErrorTypeEnum;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;

import java.time.Instant;

@Log4j2
@Getter
public class CustomException extends RuntimeException {


  private final Instant timestamp;
  private final HttpStatus status;
  private final String error;
  private final String message;

  public CustomException(ErrorTypeEnum errorTypeEnum, String message) {
    super(message);
    this.timestamp = Instant.now();
    this.status = errorTypeEnum.getStatus();
    this.error = errorTypeEnum.getType();
    this.message = message;
    log.error(error + ": " + message);
  }

  @Override
  public String toString() {
    return "CustomException{" +
        "timestamp=" + timestamp +
        ", status=" + status +
        ", error='" + error + '\'' +
        ", message='" + message + '\'' +
        '}';
  }

}
