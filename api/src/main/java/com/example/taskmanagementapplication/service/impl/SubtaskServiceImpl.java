package com.example.taskmanagementapplication.service.impl;

import com.example.taskmanagementapplication.domain.dto.SubtaskDto;
import com.example.taskmanagementapplication.entity.Subtask;
import com.example.taskmanagementapplication.entity.Task;
import com.example.taskmanagementapplication.repository.SubtaskRepository;
import com.example.taskmanagementapplication.service.SubtaskService;
import com.example.taskmanagementapplication.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class SubtaskServiceImpl implements SubtaskService {

  private final SubtaskRepository subtaskRepository;
  private final TaskService taskService;

  @Override
  public Subtask create(SubtaskDto subtaskDto) {
    Subtask subtask = Subtask.builder()
        .description(subtaskDto.getDescription())
        .completed(subtaskDto.getCompleted())
        .build();

    Task task = taskService.getById(subtaskDto.getTaskId());
    subtask.setTask(task);

    return update(subtask);
  }

  @Override
  public Subtask getById(Long id) {
    Subtask subtask = subtaskRepository.getById(id);
    if (subtask == null) {
      throw new RuntimeException(format("Subtask with id '%s' was not found", id));
    }
    return subtask;
  }

  @Override
  public List<Subtask> getByTaskId(Long taskId) {

    return subtaskRepository.findAllByTaskId(taskId);
  }

  @Override
  public Subtask edit(SubtaskDto subtaskDto) {
    Subtask subtask = getById(subtaskDto.getId());

    if (subtaskDto.getTaskId() != null) {
      Task oldTask = subtask.getTask();
      oldTask.getSubtasks().remove(subtask);
      Task newTask = taskService.getById(subtaskDto.getTaskId());
      subtask.setTask(newTask);
    }
    if (subtaskDto.getDescription() != null) {
      subtask.setDescription(subtaskDto.getDescription());
    }
    if (subtaskDto.getCompleted() != null) {
      subtask.setCompleted(subtaskDto.getCompleted());
    }

    return update(subtask);
  }

  @Override
  public Subtask update(Subtask subtask) {

    return subtaskRepository.save(subtask);
  }

  @Override
  public void deleteById(Long id) {

    subtaskRepository.deleteById(id);
  }

}
