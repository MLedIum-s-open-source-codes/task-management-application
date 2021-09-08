package com.example.taskmanagementapplication.controller;

import com.example.taskmanagementapplication.annotation.UserId;
import com.example.taskmanagementapplication.domain.dto.UserDto;
import com.example.taskmanagementapplication.domain.dto.UsersDto;
import com.example.taskmanagementapplication.service.DeskUserService;
import com.example.taskmanagementapplication.service.TaskUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@Log4j2
@RestController
@Secured({"ROLE_USER"})
@RequestMapping("/desks/{deskId}/tasks/{taskId}/users")
@RequiredArgsConstructor
public class TaskUserRestController {

  private final DeskUserService deskUserService;
  private final TaskUserService taskUserService;

  @PostMapping("/{idUserBeingAdded}")
  public ResponseEntity<UserDto> addUser(
      @PathVariable Long deskId,
      @PathVariable Long taskId,
      @PathVariable Long idUserBeingAdded,
      @UserId Long userId) {

    deskUserService.checkContainsDeskWithIdUserWithId(deskId, userId);
    deskUserService.checkContainsDeskWithIdUserWithId(deskId, idUserBeingAdded);

    return ResponseEntity.ok(UserDto.of(taskUserService.create(taskId, idUserBeingAdded).getUser()));
  }

  @GetMapping
  public ResponseEntity<UsersDto> getUsers(
      @PathVariable Long deskId,
      @PathVariable Long taskId,
      @UserId Long userId) {

    deskUserService.checkContainsDeskWithIdUserWithId(deskId, userId);

    return ResponseEntity.ok(new UsersDto(
        taskUserService.getAllByTaskId(taskId).stream().map(
            taskUser -> taskUser.getUser()
        ).collect(Collectors.toList())
    ));
  }

  @DeleteMapping("/{idUserBeingDeleted}")
  public ResponseEntity<UsersDto> removeUser(
      @PathVariable Long deskId,
      @PathVariable Long taskId,
      @PathVariable Long idUserBeingDeleted,
      @UserId Long userId) {

    deskUserService.checkContainsDeskWithIdUserWithId(deskId, userId);
    deskUserService.checkContainsDeskWithIdUserWithId(deskId, idUserBeingDeleted);

    taskUserService.delete(taskId, idUserBeingDeleted);
    return ResponseEntity.ok(null);
  }

}
