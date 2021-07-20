package com.example.taskmanagementapplication.service.impl;

import com.example.taskmanagementapplication.domain.dto.UserDto;
import com.example.taskmanagementapplication.domain.request.AuthenticationRequest;
import com.example.taskmanagementapplication.entity.User;
import com.example.taskmanagementapplication.enumeration.RoleEnum;
import com.example.taskmanagementapplication.repository.UserRepository;
import com.example.taskmanagementapplication.service.RoleService;
import com.example.taskmanagementapplication.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final RoleService roleService;

  @Override
  public User create(AuthenticationRequest authenticationRequest) {
    if (existsUserWithUsername(authenticationRequest.getUsername())) {
      throw new RuntimeException(format("User with username '%s' already exists", authenticationRequest.getUsername()));
    }
    User user = User.builder()
        .username(authenticationRequest.getUsername())
        .password(authenticationRequest.getPassword())
        .enabled(true)
        .build();

    user.addRole(roleService.getRole(RoleEnum.USER.getName()));

    return update(user);
  }

  @Override
  public User getById(Long id) {
    Optional<User> user = userRepository.findById(id);
    if (user.isEmpty()) {
      throw new RuntimeException(format("User with id '%s' was not found", id));
    }
    return user.get();
  }

  @Override
  public User getByUsername(String username) {
    Optional<User> user = userRepository.findByUsername(username);
    if (user.isEmpty()) {
      throw new RuntimeException(format("User with username '%s' was not found", username));
    }
    return user.get();
  }

  @Override
  public User edit(UserDto userDto) {
    User user = getById(userDto.getId());

    return update(userDto.toDomain());
  }

  @Override
  public boolean existsUserWithUsername(String username) {

    return userRepository.existsByUsername(username);
  }

  @Override
  public User update(User user) {

    return userRepository.save(user);
  }

}
