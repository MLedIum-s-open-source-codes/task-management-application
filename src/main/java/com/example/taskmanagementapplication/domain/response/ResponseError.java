package com.example.taskmanagementapplication.domain.response;

import lombok.Builder;
import lombok.Getter;

import java.time.Instant;

@Getter
@Builder
public class ResponseError {

  private final String path;
  private final Instant timestamp;
  private final int status;
  private final String error;
  private final String message;

}
