package com.example.taskmanagementapplication.service.impl;

import com.example.taskmanagementapplication.domain.request.EditSubtaskRequest;
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
  public Subtask create(EditSubtaskRequest editSubtaskRequest) {
    Subtask subtask = Subtask.builder()
        .description(editSubtaskRequest.getDescription())
        .isCompleted(editSubtaskRequest.getCompleted())
        .build();

    Task task = taskService.getById(editSubtaskRequest.getTaskId());
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
  public Subtask edit(EditSubtaskRequest editSubtaskRequest) {
    Subtask subtask = getById(editSubtaskRequest.getId());

    if (editSubtaskRequest.getTaskId() != null) {
      Task oldTask = subtask.getTask();
      oldTask.getSubtasks().remove(subtask);
      Task newTask = taskService.getById(editSubtaskRequest.getTaskId());
      subtask.setTask(newTask);
    }
    if (editSubtaskRequest.getDescription() != null) {
      subtask.setDescription(editSubtaskRequest.getDescription());
    }
    if (editSubtaskRequest.getCompleted() != null) {
      subtask.setCompleted(editSubtaskRequest.getCompleted());
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
