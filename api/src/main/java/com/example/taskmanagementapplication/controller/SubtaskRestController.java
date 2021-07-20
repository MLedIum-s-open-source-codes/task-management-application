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
@RequestMapping("/subtask")
@RequiredArgsConstructor
public class SubtaskRestController {

  private final SubtaskService subtaskService;

  @PostMapping
  public ResponseEntity<?> createSubtask(@RequestBody SubtaskDto subtaskDto, @UserId Long userId) {

    return ResponseEntity.ok().build();
  }

  @GetMapping
  public ResponseEntity<?> getSubtasksByTaskId(@RequestBody Long taskId, @UserId Long userId) {

    return ResponseEntity.ok().build();
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> getSubtask(@PathVariable Long id, @UserId Long userId) {

    return ResponseEntity.ok().build();
  }

  @PutMapping
  public ResponseEntity<?> editSubtask(@RequestBody SubtaskDto subtaskDto, @UserId Long userId) {

    return ResponseEntity.ok().build();
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> hideSubtask(@PathVariable Long id, @UserId Long userId) {

    subtaskService.deleteById(id);
    return ResponseEntity.ok().build();
  }

}
