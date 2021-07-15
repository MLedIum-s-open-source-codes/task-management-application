package com.example.taskmanagementapplication.controller;

import com.example.taskmanagementapplication.domain.dto.SubtaskDto;
import com.example.taskmanagementapplication.domain.dto.SubtasksDto;
import com.example.taskmanagementapplication.domain.request.EditSubtaskRequest;
import com.example.taskmanagementapplication.service.SubtaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/subtask")
@RequiredArgsConstructor
public class SubtaskController {

  private final SubtaskService subtaskService;

  @GetMapping
  public ResponseEntity<SubtasksDto> getSubtasksByTaskId(@RequestBody Long taskId) {

    return ResponseEntity.ok(new SubtasksDto(subtaskService.getByTaskId(taskId)));
  }

  @GetMapping("/{id}")
  public ResponseEntity<SubtaskDto> getSubtask(@PathVariable Long id) {

    return ResponseEntity.ok(SubtaskDto.of(subtaskService.getById(id)));
  }

  @PostMapping
  public ResponseEntity<SubtaskDto> createSubtask(@RequestBody EditSubtaskRequest editSubtaskRequest) {

    return ResponseEntity.ok(SubtaskDto.of(subtaskService.create(editSubtaskRequest)));
  }

  @PutMapping
  public ResponseEntity<SubtaskDto> editSubtask(@RequestBody EditSubtaskRequest editSubtaskRequest) {

    return ResponseEntity.ok(SubtaskDto.of(subtaskService.edit(editSubtaskRequest)));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<HttpStatus> hideSubtask(@PathVariable Long id) {

    subtaskService.deleteById(id);
    return ResponseEntity.ok(HttpStatus.OK);
  }

}
