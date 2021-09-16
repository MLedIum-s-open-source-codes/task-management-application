package com.example.taskmanagementapplication.domain.dto;

import com.example.taskmanagementapplication.entity.Desk;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeskDto {

  private Long id;

  private String name;

  private List<TaskDto> tasks;

  private List<UserDto> users;

  public static DeskDto of(Desk desk) {
    return DeskDto.builder()
        .id(desk.getId())
        .name(desk.getName())
        .tasks(desk.getTasks() == null ? null : desk.getTasks().stream().map(TaskDto::of).collect(Collectors.toList()))
        .users(desk.getDeskUsers() == null ? null : desk.getDeskUsers().stream().map(
            deskUser -> UserDto.of(deskUser.getUser())
        ).collect(Collectors.toList()))
        .build();
  }

  public Desk toDomain() {
    Desk desk = Desk.builder()
        .id(id)
        .name(name)
        .build();
    desk.setTasks(tasks == null ? null : tasks.stream().map(taskDto -> taskDto.toDomain(desk)).collect(Collectors.toSet()));

    return desk;
  }

}
