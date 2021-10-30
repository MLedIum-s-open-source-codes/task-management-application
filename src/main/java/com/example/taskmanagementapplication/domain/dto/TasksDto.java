package com.example.taskmanagementapplication.domain.dto;

import com.example.taskmanagementapplication.entity.Task;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class TasksDto {

  private List<TaskDto> tasks;

  public TasksDto(List<Task> tasks) {
    this.tasks = tasks == null ? null : tasks.stream().map(TaskDto::of).collect(Collectors.toList());
  }

}
