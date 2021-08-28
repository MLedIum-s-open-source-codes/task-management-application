package com.example.taskmanagementapplication.controller;

import com.example.taskmanagementapplication.annotation.UserId;
import com.example.taskmanagementapplication.domain.dto.TaskDto;
import com.example.taskmanagementapplication.domain.dto.TasksDto;
import com.example.taskmanagementapplication.service.DeskUserService;
import com.example.taskmanagementapplication.service.TaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@Secured({"ROLE_USER"})
@RequestMapping("/desks/{deskId}/tasks")
@RequiredArgsConstructor
public class TaskRestController {

  private final TaskService taskService;
  private final DeskUserService deskUserService;

  @PostMapping
  public ResponseEntity<TaskDto> createTask(
      @PathVariable Long deskId,
      @RequestBody TaskDto taskDto,
      @UserId Long userId) {

    deskUserService.get(deskId, userId);

    taskDto.setDeskId(deskId);
    return ResponseEntity.ok(TaskDto.of(taskService.create(taskDto)));
  }

  @GetMapping
  public ResponseEntity<TasksDto> getTasksByDeskId(
      @PathVariable Long deskId,
      @UserId Long userId) {

    deskUserService.get(deskId, userId);

    return ResponseEntity.ok(new TasksDto(taskService.getAllByDeskId(deskId)));
  }

  @GetMapping("/{id}")
  public ResponseEntity<TaskDto> getTask(
      @PathVariable Long deskId,
      @PathVariable Long id,
      @UserId Long userId) {

    deskUserService.get(deskId, userId);

    return ResponseEntity.ok(TaskDto.of(taskService.get(id)));
  }

  @PutMapping("/{id}")
  public ResponseEntity<TaskDto> editTask(
      @PathVariable Long deskId,
      @RequestBody TaskDto taskDto,
      @UserId Long userId) {

    deskUserService.get(deskId, userId);

    return ResponseEntity.ok(TaskDto.of(taskService.edit(taskDto)));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<HttpStatus> deleteTask(
      @PathVariable Long deskId,
      @PathVariable Long id,
      @UserId Long userId) {

    deskUserService.get(deskId, userId);

    taskService.delete(id);
    return ResponseEntity.ok().build();
  }

}
