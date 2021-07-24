package com.example.taskmanagementapplication.domain.dto;

import com.example.taskmanagementapplication.entity.Desk;
import com.example.taskmanagementapplication.entity.Task;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskDto {

  private Long id;

  private String name;

  private String note;

  private Boolean completed;

  private Boolean important;

  private Instant completionDate;

  private Long deskId;

  private List<SubtaskDto> subtasks;

  public static TaskDto of(Task task) {
    return TaskDto.builder()
        .id(task.getId())
        .name(task.getName())
        .note(task.getNote())
        .completed(task.getCompleted())
        .completionDate(task.getCompletionDate())
        .subtasks(task.getSubtasks() == null ? null : task.getSubtasks().stream().map(SubtaskDto::of).collect(Collectors.toList()))
        .build();
  }

  public Task toDomain(Desk desk) {
    Task task = Task.builder()
        .id(id)
        .name(name)
        .note(note)
        .completed(completed)
        .completionDate(completionDate)
        .desk(desk)
        .build();
    task.setSubtasks(subtasks == null ? null : subtasks.stream().map(subtaskDto -> subtaskDto.toDomain(task)).collect(Collectors.toSet()));

    return task;
  }

}
