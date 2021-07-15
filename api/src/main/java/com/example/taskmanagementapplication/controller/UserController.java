package com.example.taskmanagementapplication.controller;

import com.example.taskmanagementapplication.domain.dto.UserDto;
import com.example.taskmanagementapplication.domain.request.EditUserRequest;
import com.example.taskmanagementapplication.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  @PostMapping
  public ResponseEntity<UserDto> createUser(EditUserRequest editUserRequest) {

    return ResponseEntity.ok(UserDto.of(userService.create(editUserRequest)));
  }

}
