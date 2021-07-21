package com.example.taskmanagementapplication.controller;

import com.example.taskmanagementapplication.annotation.UserId;
import com.example.taskmanagementapplication.domain.dto.SubtaskDto;
import com.example.taskmanagementapplication.domain.dto.SubtasksDto;
import com.example.taskmanagementapplication.service.SubtaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@Secured({"ROLE_USER"})
@RequestMapping("/desks/{deskId}/tasks/{taskId}/subtasks")
@RequiredArgsConstructor
public class SubtaskRestController {

  private final SubtaskService subtaskService;

  @PostMapping
  public ResponseEntity<SubtaskDto> createSubtask(
      @RequestBody SubtaskDto subtaskDto,
      @UserId Long userId) {

    // проверка
    return ResponseEntity.ok(SubtaskDto.of(subtaskService.create(subtaskDto)));
  }

  @GetMapping
  public ResponseEntity<SubtasksDto> getSubtasksByTaskId(
      @PathVariable Long taskId,
      @UserId Long userId) {

    // проверка
    return ResponseEntity.ok(new SubtasksDto(subtaskService.getAllByTaskId(taskId)));
  }

  @GetMapping("/{id}")
  public ResponseEntity<SubtaskDto> getSubtask(
      @PathVariable Long id,
      @UserId Long userId) {

    // проверка
    return ResponseEntity.ok(SubtaskDto.of(subtaskService.get(id)));
  }

  @PutMapping
  public ResponseEntity<SubtaskDto> editSubtask(
      @RequestBody SubtaskDto subtaskDto,
      @UserId Long userId) {

    // проверка
    return ResponseEntity.ok(SubtaskDto.of(subtaskService.edit(subtaskDto)));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<HttpStatus> hideSubtask(
      @PathVariable Long id,
      @UserId Long userId) {

    // проверка
    subtaskService.delete(id);
    return ResponseEntity.ok().build();
  }

}
