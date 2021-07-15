package com.example.taskmanagementapplication.service.impl;

import com.example.taskmanagementapplication.domain.request.EditUserRequest;
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
  public User create(EditUserRequest editUserRequest) {
    User user = User.builder()
        .username(editUserRequest.getUsername())
        .build();

    return update(user);
  }

  @Override
  public User getById(Long id) {
    User user = userRepository.getById(id);
    if (user == null) {
      throw new RuntimeException(format("User with id '%s' was not found", id));
    }
    return user;
  }

  @Override
  public User edit(EditUserRequest editUserRequest) {
    User user = getById(editUserRequest.getId());

    if (editUserRequest.getUsername() != null) {
      user.setUsername(editUserRequest.getUsername());
    }

    return update(user);
  }

  @Override
  public User update(User user) {
    return userRepository.save(user);
  }

}
