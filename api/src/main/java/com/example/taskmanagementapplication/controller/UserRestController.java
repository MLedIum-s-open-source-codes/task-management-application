package com.example.taskmanagementapplication.controller;

import com.example.taskmanagementapplication.domain.dto.UserDto;
import com.example.taskmanagementapplication.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Secured({"ROLE_USER"})
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserRestController {

  private final UserService userService;



}
