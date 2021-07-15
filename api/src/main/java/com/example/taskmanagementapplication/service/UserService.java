package com.example.taskmanagementapplication.service;

import com.example.taskmanagementapplication.domain.request.EditUserRequest;
import com.example.taskmanagementapplication.entity.User;

public interface UserService {

  User create(EditUserRequest editUserRequest);

  User getById(Long id);

  User edit(EditUserRequest editUserRequest);

  User update(User user);

}
