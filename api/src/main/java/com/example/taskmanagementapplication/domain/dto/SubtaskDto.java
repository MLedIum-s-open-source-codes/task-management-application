package com.example.taskmanagementapplication.domain.dto;

import com.example.taskmanagementapplication.entity.Subtask;
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

  private boolean isCompleted;

  public static SubtaskDto of(Subtask subtask) {
    return SubtaskDto.builder()
        .id(subtask.getId())
        .description(subtask.getDescription())
        .isCompleted(subtask.isCompleted())
        .build();
  }

}
