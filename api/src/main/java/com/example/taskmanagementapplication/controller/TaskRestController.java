package com.example.taskmanagementapplication.controller;

import com.example.taskmanagementapplication.annotation.UserId;
import com.example.taskmanagementapplication.domain.dto.TaskDto;
import com.example.taskmanagementapplication.domain.dto.TasksDto;
import com.example.taskmanagementapplication.service.TaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@Secured({"ROLE_USER"})
@RequestMapping("/task")
@RequiredArgsConstructor
public class TaskRestController {

  private final TaskService taskService;

  @PostMapping
  public ResponseEntity<TaskDto> createTask(@RequestBody TaskDto taskDto, @UserId Long userId) {

    return ResponseEntity.ok().build();
  }

  @GetMapping
  public ResponseEntity<TasksDto> getTasksByDeskId(@RequestBody Long deskId, @UserId Long userId) {

    return ResponseEntity.ok().build();
  }

  @GetMapping("/{id}")
  public ResponseEntity<TaskDto> getTask(@PathVariable Long id, @UserId Long userId) {

    return ResponseEntity.ok().build();
  }

  @PutMapping
  public ResponseEntity<TaskDto> editTask(@RequestBody TaskDto taskDto, @UserId Long userId) {

    return ResponseEntity.ok().build();
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<HttpStatus> hideTask(@PathVariable Long id, @UserId Long userId) {

    taskService.deleteById(id);
    return ResponseEntity.ok().build();
  }

}
