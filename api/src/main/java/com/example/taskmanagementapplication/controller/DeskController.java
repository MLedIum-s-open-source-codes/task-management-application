package com.example.taskmanagementapplication.controller;

import com.example.taskmanagementapplication.domain.dto.DeskDto;
import com.example.taskmanagementapplication.domain.dto.DesksDto;
import com.example.taskmanagementapplication.domain.request.EditDeskRequest;
import com.example.taskmanagementapplication.service.DeskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/desk")
@RequiredArgsConstructor
public class DeskController {

  private final DeskService deskService;

  @GetMapping
  public ResponseEntity<DesksDto> getDesksByUserId(@RequestHeader Long userId) {

    return ResponseEntity.ok(new DesksDto(deskService.getByUserId(userId)));
  }

  @GetMapping("/{id}")
  public ResponseEntity<DeskDto> getDesk(@PathVariable Long id) {

    return ResponseEntity.ok(DeskDto.of(deskService.getById(id)));
  }

  @PostMapping
  public ResponseEntity<DeskDto> createDesk(@RequestBody EditDeskRequest editDeskRequest) {

    return ResponseEntity.ok(DeskDto.of(deskService.create(editDeskRequest)));
  }

  @PutMapping
  public ResponseEntity<DeskDto> editDesk(@RequestBody EditDeskRequest editDeskRequest) {

    return ResponseEntity.ok(DeskDto.of(deskService.edit(editDeskRequest)));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<HttpStatus> hideDesk(@PathVariable Long id) {

    deskService.deleteById(id);
    return ResponseEntity.ok(HttpStatus.OK);
  }

}
