package com.example.taskmanagementapplication.domain.dto;

import com.example.taskmanagementapplication.entity.Task;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskDto {

  private Long id;

  private String name;

  private String description;

  private boolean isCompleted;

  private List<SubtaskDto> subtasks;

  public static TaskDto of(Task task) {
    return TaskDto.builder()
        .id(task.getId())
        .name(task.getName())
        .description(task.getDescription())
        .isCompleted(task.isCompleted())
        .subtasks(task.getSubtasks().stream().map(SubtaskDto::of).toList())
        .build();
  }

}
