package com.example.taskmanagementapplication.controller;

import com.example.taskmanagementapplication.annotation.UserId;
import com.example.taskmanagementapplication.domain.dto.UserDto;
import com.example.taskmanagementapplication.domain.dto.UsersDto;
import com.example.taskmanagementapplication.domain.request.DeskUserRequest;
import com.example.taskmanagementapplication.enumeration.ErrorTypeEnum;
import com.example.taskmanagementapplication.exception.CustomException;
import com.example.taskmanagementapplication.service.DeskUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

import static java.lang.String.format;

@RestController
@Secured({"ROLE_USER"})
@RequestMapping("/desks/{deskId}/users")
@RequiredArgsConstructor
public class DeskUserRestController {

  private final DeskUserService deskUserService;

  @PostMapping
  public ResponseEntity<UserDto> addUser(
      @PathVariable Long deskId,
      @RequestBody DeskUserRequest deskUserRequest,
      @UserId Long userId) {
    if (!deskUserService.get(deskId, userId).getOwner()) {
      throw new CustomException(ErrorTypeEnum.ACCESS_DENIED, format("User with id '%s' hasn't access to this action", userId));
    }
    return ResponseEntity.ok(UserDto.of(deskUserService.create(deskId, deskUserRequest.getUserId()).getUser()));
  }

  @GetMapping
  public ResponseEntity<UsersDto> getUsers(
      @PathVariable Long deskId,
      @UserId Long userId) {

    deskUserService.get(deskId, userId);

    return ResponseEntity.ok(new UsersDto(
        deskUserService.getAllByDeskId(deskId).stream().map(
            deskUser -> deskUser.getUser()
        ).collect(Collectors.toList())
    ));
  }

  @PutMapping
  public ResponseEntity<UsersDto> changeOwner(
      @PathVariable Long deskId,
      @RequestBody DeskUserRequest deskUserRequest,
      @UserId Long userId) {

    if (!deskUserService.get(deskId, userId).getOwner()) {
      throw new CustomException(ErrorTypeEnum.ACCESS_DENIED, format("User with id '%s' hasn't access to this action", userId));
    }

    return ResponseEntity.ok(new UsersDto(
        deskUserService.changeOwner(deskId, deskUserRequest.getUserId(), userId).stream().map(
            deskUser -> deskUser.getUser()
        ).collect(Collectors.toList())
    ));
  }

  @DeleteMapping
  public ResponseEntity<HttpStatus> removeUser(
      @PathVariable Long deskId,
      @RequestBody DeskUserRequest deskUserRequest,
      @UserId Long userId) {
    if (!deskUserService.get(deskId, userId).getOwner() && deskUserRequest.getUserId() != userId) {
      throw new CustomException(ErrorTypeEnum.ACCESS_DENIED, format("User with id '%s' hasn't access to this action", userId));
    }
    deskUserService.delete(deskId, deskUserRequest.getUserId());
    return ResponseEntity.ok().build();
  }

}
