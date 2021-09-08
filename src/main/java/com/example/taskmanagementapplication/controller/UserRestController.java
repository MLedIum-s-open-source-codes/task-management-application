package com.example.taskmanagementapplication.controller;

import com.example.taskmanagementapplication.annotation.UserId;
import com.example.taskmanagementapplication.domain.dto.UserDto;
import com.example.taskmanagementapplication.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@Secured({"ROLE_USER"})
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserRestController {

  private final UserService userService;

  @GetMapping
  public ResponseEntity<UserDto> getUser(@UserId Long id) {

    return ResponseEntity.ok(UserDto.of(userService.get(id)));
  }

  @GetMapping("/{id}")
  public ResponseEntity<UserDto> getUserById(@PathVariable Long id, @UserId Long userId) {

    return ResponseEntity.ok(UserDto.of(userService.get(id)));
  }

  @PutMapping
  public ResponseEntity<UserDto> editUser(@RequestBody UserDto dto, @UserId Long userId) {

    dto.setId(userId);
    return ResponseEntity.ok(UserDto.of(userService.edit(dto)));
  }

}
