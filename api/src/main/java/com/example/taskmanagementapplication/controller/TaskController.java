package com.example.taskmanagementapplication.controller;

import com.example.taskmanagementapplication.domain.dto.TaskDto;
import com.example.taskmanagementapplication.domain.dto.TasksDto;
import com.example.taskmanagementapplication.domain.request.EditTaskRequest;
import com.example.taskmanagementapplication.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/task")
@RequiredArgsConstructor
public class TaskController {

  private final TaskService taskService;

  @GetMapping
  public ResponseEntity<TasksDto> getTasksByDeskId(@RequestBody Long deskId) {

    return ResponseEntity.ok(new TasksDto(taskService.getByDeskId(deskId)));
  }

  @GetMapping("/{id}")
  public ResponseEntity<TaskDto> getTask(@PathVariable Long id) {

    return ResponseEntity.ok(TaskDto.of(taskService.getById(id)));
  }

  @PostMapping
  public ResponseEntity<TaskDto> createTask(@RequestBody EditTaskRequest editTaskRequest) {

    return ResponseEntity.ok(TaskDto.of(taskService.create(editTaskRequest)));
  }

  @PutMapping
  public ResponseEntity<TaskDto> editTask(@RequestBody EditTaskRequest editTaskRequest) {

    return ResponseEntity.ok(TaskDto.of(taskService.edit(editTaskRequest)));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<HttpStatus> hideTask(@PathVariable Long id) {

    taskService.deleteById(id);
    return ResponseEntity.ok(HttpStatus.OK);
  }

}
