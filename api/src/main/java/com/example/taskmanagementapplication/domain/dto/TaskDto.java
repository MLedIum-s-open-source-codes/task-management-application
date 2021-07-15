package com.example.taskmanagementapplication.domain.dto;

import com.example.taskmanagementapplication.entity.Task;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskDto {

  private Long id;

  private String name;

  private String description;

  private boolean isCompleted;

  private SubtasksDto subtasks;

  public static TaskDto of(Task task) {
    return TaskDto.builder()
        .name(task.getName())
        .description(task.getDescription())
        .isCompleted(task.isCompleted())
        .subtasks(new SubtasksDto(task.getSubtasks().stream().toList()))
        .build();
  }

}
