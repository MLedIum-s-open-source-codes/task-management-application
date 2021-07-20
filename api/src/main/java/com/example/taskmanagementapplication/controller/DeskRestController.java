package com.example.taskmanagementapplication.controller;

import com.example.taskmanagementapplication.annotation.UserId;
import com.example.taskmanagementapplication.domain.dto.DeskDto;
import com.example.taskmanagementapplication.domain.dto.DesksDto;
import com.example.taskmanagementapplication.service.DeskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@Secured({"ROLE_USER"})
@RequestMapping("/desk")
@RequiredArgsConstructor
public class DeskRestController {

  private final DeskService deskService;

  @PostMapping
  public ResponseEntity<?> createDesk(@RequestBody DeskDto deskDto, @UserId Long userId) {

    return ResponseEntity.ok().build();
  }

  @GetMapping
  public ResponseEntity<?> getDesksByUserId(@UserId Long userId) {

    return ResponseEntity.ok().build();
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> getDesk(@PathVariable Long id, @UserId Long userId) {

    return ResponseEntity.ok().build();
  }

  @PutMapping
  public ResponseEntity<?> editDesk(@RequestBody DeskDto deskDto, @UserId Long userId) {

    return ResponseEntity.ok().build();
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> hideDesk(@PathVariable Long id, @UserId Long userId) {

    deskService.deleteById(id);
    return ResponseEntity.ok().build();
  }

}
