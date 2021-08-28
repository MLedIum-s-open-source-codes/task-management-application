package com.example.taskmanagementapplication.domain.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AuthenticationResponse {

  private final String token;

}
