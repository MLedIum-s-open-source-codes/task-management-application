package com.example.taskmanagementapplication.service.impl;

import com.example.taskmanagementapplication.domain.request.AuthenticationRequest;
import com.example.taskmanagementapplication.domain.response.AuthenticationResponse;
import com.example.taskmanagementapplication.entity.User;
import com.example.taskmanagementapplication.enumeration.ErrorTypeEnum;
import com.example.taskmanagementapplication.exception.CustomException;
import com.example.taskmanagementapplication.security.token.JwtTokenProvider;
import com.example.taskmanagementapplication.service.AuthenticationService;
import com.example.taskmanagementapplication.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import static java.lang.String.format;

@Log4j2
@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

  private final JwtTokenProvider jwtTokenProvider;
  private final UserService userService;
  private final BCryptPasswordEncoder passwordEncoder;

  @Override
  public AuthenticationResponse login(AuthenticationRequest authenticationRequest) {
    User user = userService.getByUsername(authenticationRequest.getUsername());

    if (!passwordEncoder.matches(authenticationRequest.getPassword(), user.getPassword())) {
      throw new CustomException(ErrorTypeEnum.INCORRECT_LOGIN_OR_PASSWORD, format("Incorrect login or password"));
    }

    String token = jwtTokenProvider.createToken(user.getUsername());

    return AuthenticationResponse.builder()
        .token(token)
        .build();
  }

  @Override
  public AuthenticationResponse register(AuthenticationRequest authenticationRequest) {
    if (userService.existsUserWithUsername(authenticationRequest.getUsername())) {
      throw new CustomException(ErrorTypeEnum.ALREADY_EXIST, format("User with username '%s' already exist"));
    }
    authenticationRequest.setPassword(passwordEncoder.encode(authenticationRequest.getPassword()));
    User user = userService.create(authenticationRequest);

    String token = jwtTokenProvider.createToken(user.getUsername());

    return AuthenticationResponse.builder()
        .token(token)
        .build();
  }

}
