package com.example.taskmanagementapplication.controller;

import com.example.taskmanagementapplication.domain.request.AuthenticationRequest;
import com.example.taskmanagementapplication.domain.response.AuthenticationResponse;
import com.example.taskmanagementapplication.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationRestController {

  private final AuthenticationService authenticationService;

  @PostMapping("/login")
  public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest authenticationRequest) {

    return ResponseEntity.ok(authenticationService.login(authenticationRequest));
  }

  @PostMapping("/register")
  public ResponseEntity<AuthenticationResponse> register(@RequestBody AuthenticationRequest authenticationRequest) {

    return ResponseEntity.ok(authenticationService.register(authenticationRequest));
  }

  @DeleteMapping("/logout")
  public ResponseEntity<HttpStatus> logout(HttpServletRequest request) {
    authenticationService.logout(request);
    return ResponseEntity.ok().build();
  }

}
