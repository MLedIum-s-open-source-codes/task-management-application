package com.example.taskmanagementapplication.controller;

import com.example.taskmanagementapplication.annotation.UserId;
import com.example.taskmanagementapplication.domain.dto.SubtaskDto;
import com.example.taskmanagementapplication.domain.dto.SubtasksDto;
import com.example.taskmanagementapplication.service.DeskUserService;
import com.example.taskmanagementapplication.service.SubtaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@Secured({"ROLE_USER"})
@RequestMapping("/desks/{deskId}/tasks/{taskId}/subtasks")
@RequiredArgsConstructor
public class SubtaskRestController {

  private final SubtaskService subtaskService;
  private final DeskUserService deskUserService;

  @PostMapping
  public ResponseEntity<SubtaskDto> createSubtask(
      @PathVariable Long deskId,
      @PathVariable Long taskId,
      @RequestBody SubtaskDto dto,
      @UserId Long userId
  ) {

    checkAccess(deskId, userId);

    dto.setTaskId(taskId);
    return ResponseEntity.ok(SubtaskDto.of(subtaskService.create(dto)));
  }

  @GetMapping
  public ResponseEntity<SubtasksDto> getSubtasksByTaskId(
      @PathVariable Long deskId,
      @PathVariable Long taskId,
      @UserId Long userId
  ) {

    checkAccess(deskId, userId);

    return ResponseEntity.ok(new SubtasksDto(subtaskService.getAllByTaskId(taskId)));
  }

  @GetMapping("/{id}")
  public ResponseEntity<SubtaskDto> getSubtask(
      @PathVariable Long deskId,
      @PathVariable Long taskId,
      @PathVariable Long id,
      @UserId Long userId
  ) {

    checkAccess(deskId, userId);

    return ResponseEntity.ok(SubtaskDto.of(subtaskService.get(id)));
  }

  @PutMapping("/{id}")
  public ResponseEntity<SubtaskDto> editSubtask(
      @PathVariable Long deskId,
      @PathVariable Long taskId,
      @PathVariable Long id,
      @RequestBody SubtaskDto dto,
      @UserId Long userId
  ) {

    checkAccess(deskId, userId);

    dto.setId(id);
    dto.setTaskId(taskId);
    return ResponseEntity.ok(SubtaskDto.of(subtaskService.edit(dto)));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<HttpStatus> deleteSubtask(
      @PathVariable Long deskId,
      @PathVariable Long taskId,
      @PathVariable Long id,
      @UserId Long userId
  ) {

    checkAccess(deskId, userId);

    subtaskService.delete(id);
    return ResponseEntity.ok().build();
  }

  private void checkAccess(Long deskId, Long userId) {

    deskUserService.checkContainsDeskWithIdUserWithId(deskId, userId);
  }

}
