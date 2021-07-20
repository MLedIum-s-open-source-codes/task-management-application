package com.example.taskmanagementapplication.domain.dto;

import com.example.taskmanagementapplication.entity.Subtask;
import com.example.taskmanagementapplication.entity.Task;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubtaskDto {

  private Long id;

  private String description;

  private Boolean completed;

  private Long taskId;

  public static SubtaskDto of(Subtask subtask) {
    return SubtaskDto.builder()
        .id(subtask.getId())
        .description(subtask.getDescription())
        .completed(subtask.isCompleted())
        .build();
  }

  public Subtask toDomain(Task task) {
    return Subtask.builder()
        .id(id)
        .description(description)
        .completed(completed)
        .task(task)
        .build();
  }

}
