package com.example.taskmanagementapplication.domain.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@Getter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ResponseError {

  final String path;
  final Instant timestamp;
  final int status;
  final String error;
  final String message;

}
