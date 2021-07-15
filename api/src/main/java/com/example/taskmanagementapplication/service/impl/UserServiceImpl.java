package com.example.taskmanagementapplication.service.impl;

import com.example.taskmanagementapplication.entity.User;
import com.example.taskmanagementapplication.repository.UserRepository;
import com.example.taskmanagementapplication.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  @Override
  public User getById(Long id) {
    User user = userRepository.getById(id);
    if (user == null) {
      throw new RuntimeException(format("User with id '%s' was not found", id));
    }
    return user;
  }

  @Override
  public User update(User user) {
    return userRepository.save(user);
  }

}
