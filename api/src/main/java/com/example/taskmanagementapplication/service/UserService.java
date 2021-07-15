package com.example.taskmanagementapplication.service;

import com.example.taskmanagementapplication.entity.User;

public interface UserService {

  User getById(Long id);

  User update(User user);

}
