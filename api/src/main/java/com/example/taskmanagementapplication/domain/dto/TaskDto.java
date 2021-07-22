package com.example.taskmanagementapplication.domain.dto;

import com.example.taskmanagementapplication.entity.Desk;
import com.example.taskmanagementapplication.entity.Task;
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
public class TaskDto {

  private Long id;

  private String name;

  private String description;

  private Boolean completed;

  private Long deskId;

  private List<SubtaskDto> subtasks;

  public static TaskDto of(Task task) {
    return TaskDto.builder()
        .id(task.getId())
        .name(task.getName())
        .description(task.getDescription())
        .completed(task.getCompleted())
        .subtasks(task.getSubtasks() == null ? null : task.getSubtasks().stream().map(SubtaskDto::of).collect(Collectors.toList()))
        .build();
  }

  public Task toDomain(Desk desk) {
    Task task = Task.builder()
        .id(id)
        .name(name)
        .description(description)
        .completed(completed)
        .desk(desk)
        .build();
    task.setSubtasks(subtasks == null ? null : subtasks.stream().map(subtaskDto -> subtaskDto.toDomain(task)).collect(Collectors.toSet()));

    return task;
  }

}
