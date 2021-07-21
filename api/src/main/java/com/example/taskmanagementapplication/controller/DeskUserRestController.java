package com.example.taskmanagementapplication.controller;

import com.example.taskmanagementapplication.annotation.UserId;
import com.example.taskmanagementapplication.domain.dto.DeskDto;
import com.example.taskmanagementapplication.domain.dto.UserDto;
import com.example.taskmanagementapplication.entity.Desk;
import com.example.taskmanagementapplication.enumeration.ErrorTypeEnum;
import com.example.taskmanagementapplication.exception.CustomException;
import com.example.taskmanagementapplication.service.DeskUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

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
      @RequestParam Long addingUserId,
      @UserId Long userId) {

    if (!deskUserService.get(deskId, userId).getOwner()) {
      throw new CustomException(ErrorTypeEnum.ACCESS_DENIED, format("User with id '%s' hasn't access to this action", userId));
    }
    return ResponseEntity.ok(UserDto.of(deskUserService.create(deskId, addingUserId).getUser()));
  }

  @DeleteMapping
  public ResponseEntity<HttpStatus> removeUser(
      @PathVariable Long deskId,
      @RequestParam Long removeUserId,
      @UserId Long userId) {

    if (!deskUserService.get(deskId, userId).getOwner() || removeUserId != userId) {
      throw new CustomException(ErrorTypeEnum.ACCESS_DENIED, format("User with id '%s' hasn't access to this action", userId));
    }
    deskUserService.delete(deskId, removeUserId);
    return ResponseEntity.ok().build();
  }

}
