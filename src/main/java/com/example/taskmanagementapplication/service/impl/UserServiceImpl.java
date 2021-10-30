package com.example.taskmanagementapplication.service.impl;

import com.example.taskmanagementapplication.domain.dto.UserDto;
import com.example.taskmanagementapplication.domain.request.AuthenticationRequest;
import com.example.taskmanagementapplication.entity.User;
import com.example.taskmanagementapplication.enumeration.ErrorTypeEnum;
import com.example.taskmanagementapplication.enumeration.RoleEnum;
import com.example.taskmanagementapplication.exception.CustomException;
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
      throw new CustomException(
          ErrorTypeEnum.ALREADY_EXIST,
          format("User with username '%s' already exists", authenticationRequest.getUsername())
      );
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
  public User get(Long id) {
    Optional<User> optional = userRepository.findById(id);
    if (optional.isPresent())
        return optional.get();

    throw new CustomException(
        ErrorTypeEnum.NOT_FOUND,
        format("User with id '%s' was not found", id)
    );
  }

  @Override
  public User getByUsername(String username) {
    Optional<User> optional = userRepository.findByUsernameIgnoreCase(username);
    if (optional.isPresent())
        return optional.get();

    throw new CustomException(
        ErrorTypeEnum.NOT_FOUND,
        format("User with username '%s' was not found", username)
    );
  }

  @Override
  public User edit(UserDto dto) {
    User user = get(dto.getId());
    if (dto.getUsername() != null) {
      user.setUsername(dto.getUsername());
    }

    return update(user);
  }

  @Override
  public void checkExistsUserWithId(Long id) {
    if (existsUserWithId(id)) return;

    throw new CustomException(
        ErrorTypeEnum.NOT_FOUND,
        format("User with id '%s' was not found", id)
    );
  }

  @Override
  public void checkExistsUserWithUsername(String username) {
    if (existsUserWithUsername(username)) return;

    throw new CustomException(
        ErrorTypeEnum.NOT_FOUND,
        format("User with username '%s' was not found", username)
    );
  }

  @Override
  public boolean existsUserWithId(Long id) {

    return userRepository.existsById(id);
  }

  @Override
  public boolean existsUserWithUsername(String username) {

    return userRepository.existsByUsernameIgnoreCase(username);
  }

  @Override
  public User update(User user) {

    return userRepository.save(user);
  }

}
