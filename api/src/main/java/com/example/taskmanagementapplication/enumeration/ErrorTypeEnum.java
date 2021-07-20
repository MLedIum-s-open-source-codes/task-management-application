package com.example.taskmanagementapplication.enumeration;

import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum ErrorTypeEnum {


  ALREADY_EXIST("ALREADY EXIST", HttpStatus.UNPROCESSABLE_ENTITY),
  ACCESS_DENIED("ACCESS DENIED", HttpStatus.FORBIDDEN),
  BAD_REQUEST("BAD REQUEST", HttpStatus.BAD_REQUEST),
  DUPLICATE("DUPLICATE", HttpStatus.UNPROCESSABLE_ENTITY),
  INCORRECT_LOGIN_OR_PASSWORD("INCORRECT LOGIN OR PASSWORD", HttpStatus.FORBIDDEN),
  INTERNAL_SERVER("INTERNAL SERVER ERROR", HttpStatus.INTERNAL_SERVER_ERROR),
  NOT_FOUND("NOT FOUND", HttpStatus.UNPROCESSABLE_ENTITY),
  NOT_MODIFIED("NOT MODIFIED", HttpStatus.NOT_MODIFIED),
  SAVE_TO_DB("SAVE TO DB ERROR", HttpStatus.INTERNAL_SERVER_ERROR),
  UNKNOWN_ERROR("UNKNOWN ERROR", HttpStatus.INTERNAL_SERVER_ERROR),
  VALIDATION("VALIDATION ERROR", HttpStatus.BAD_REQUEST);


  private final String type;
  private final HttpStatus status;

  ErrorTypeEnum(String type, HttpStatus status) {
    this.type = type;
    this.status = status;
  }

  public String getType() {

    return type;
  }

  public HttpStatus getStatus() {

    return status;
  }

  public static List<ErrorTypeEnum> getAll() {

    return Stream.of(ErrorTypeEnum.values()).collect(Collectors.toList());
  }

}
