package com.example.taskmanagementapplication.service;

import com.example.taskmanagementapplication.domain.request.AuthenticationRequest;
import com.example.taskmanagementapplication.domain.response.AuthenticationResponse;

public interface AuthenticationService {

  AuthenticationResponse login(AuthenticationRequest authenticationRequest);

  AuthenticationResponse register(AuthenticationRequest authenticationRequest);

}
