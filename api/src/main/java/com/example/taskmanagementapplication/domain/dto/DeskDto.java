package com.example.taskmanagementapplication.domain.dto;

import com.example.taskmanagementapplication.entity.Desk;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeskDto {

  private Long id;

  private String name;

  private String description;

  private List<TaskDto> tasks;

  public static DeskDto of(Desk desk) {
    return DeskDto.builder()
        .id(desk.getId())
        .name(desk.getName())
        .description(desk.getDescription())
        .tasks(desk.getTasks().stream().map(TaskDto::of).toList())
        .build();
  }

}
