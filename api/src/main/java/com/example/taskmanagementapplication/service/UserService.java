package com.example.taskmanagementapplication.service;

import com.example.taskmanagementapplication.domain.dto.UserDto;
import com.example.taskmanagementapplication.domain.request.AuthenticationRequest;
import com.example.taskmanagementapplication.entity.User;

public interface UserService {

  User create(AuthenticationRequest authenticationRequest);

  User getById(Long id);

  User getByUsername(String username);

  User edit(UserDto userDto);

  boolean existsUserWithUsername(String username);

  User update(User user);

}
