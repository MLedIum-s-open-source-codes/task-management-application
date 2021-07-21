package com.example.taskmanagementapplication.controller;

import com.example.taskmanagementapplication.annotation.UserId;
import com.example.taskmanagementapplication.domain.dto.DeskDto;
import com.example.taskmanagementapplication.domain.dto.DesksDto;
import com.example.taskmanagementapplication.entity.Desk;
import com.example.taskmanagementapplication.service.DeskService;
import com.example.taskmanagementapplication.service.DeskUserService;
import com.example.taskmanagementapplication.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@Secured({"ROLE_USER"})
@RequestMapping("/desks")
@RequiredArgsConstructor
public class DeskRestController {

  private final DeskService deskService;
  private final UserService userService;
  private final DeskUserService deskUserService;

  @PostMapping
  public ResponseEntity<?> createDesk(
      @RequestBody DeskDto deskDto,
      @UserId Long userId) {

    userService.get(userId);
    Desk desk = deskService.create(deskDto);
    deskUserService.create(desk.getId(), userId, true);
    return ResponseEntity.ok(DeskDto.of(desk));
  }

  @GetMapping
  public ResponseEntity<DesksDto> getAllByUserId(@UserId Long userId) {

    return ResponseEntity.ok(new DesksDto(
        deskUserService.getAllByUserId(userId).stream().map(
            deskUser -> deskUser.getDesk()
        ).collect(Collectors.toList())));
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> getDesk(
      @PathVariable Long id,
      @UserId Long userId) {

    return ResponseEntity.ok(DeskDto.of(deskService.get(id)));
  }

  @PutMapping("/{id}")
  public ResponseEntity<DeskDto> editDesk(
      @PathVariable Long id,
      @RequestBody DeskDto deskDto,
      @UserId Long userId) {

    deskDto.setId(id);
    return ResponseEntity.ok(DeskDto.of(deskService.edit(deskDto)));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<HttpStatus> deleteDesk(
      @PathVariable Long id,
      @UserId Long userId) {

    deskService.delete(id);
    return ResponseEntity.ok().build();
  }

}
