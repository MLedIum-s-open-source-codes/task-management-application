package com.example.taskmanagementapplication.domain.dto;

import com.example.taskmanagementapplication.entity.Subtask;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class SubtasksDto {

  private List<SubtaskDto> subtasks;

  public SubtasksDto(List<Subtask> subtasks) {
    this.subtasks = subtasks == null ? null : subtasks.stream().map(SubtaskDto::of).collect(Collectors.toList());
  }
}
