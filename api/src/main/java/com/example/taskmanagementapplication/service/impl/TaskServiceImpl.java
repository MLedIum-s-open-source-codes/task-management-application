package com.example.taskmanagementapplication.service.impl;

import com.example.taskmanagementapplication.domain.request.EditTaskRequest;
import com.example.taskmanagementapplication.entity.Desk;
import com.example.taskmanagementapplication.entity.Task;
import com.example.taskmanagementapplication.repository.TaskRepository;
import com.example.taskmanagementapplication.service.DeskService;
import com.example.taskmanagementapplication.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

  private final TaskRepository taskRepository;
  private final DeskService deskService;

  @Override
  public Task create(EditTaskRequest editTaskRequest) {
    Task task = Task.builder()
        .name(editTaskRequest.getName())
        .description(editTaskRequest.getDescription())
        .isCompleted(editTaskRequest.getCompleted())
        .build();

    Desk desk = deskService.getById(editTaskRequest.getDeskId());
    desk.getTasks().add(task);
    task.setDesk(desk);

    return update(task);
  }

  @Override
  public Task getById(Long id) {
    Task task = taskRepository.getById(id);
    if (task == null) {
      throw new RuntimeException(format("Task with id '%s' was not found", id));
    }
    return task;
  }

  @Override
  public List<Task> getByDeskId(Long deskId) {

    return taskRepository.findAllByDeskId(deskId);
  }

  @Override
  public Task edit(EditTaskRequest editTaskRequest) {
    Task task = getById(editTaskRequest.getId());


    if (editTaskRequest.getDeskId() != null) {
      Desk oldDesk = task.getDesk();
      oldDesk.getTasks().remove(task);
      Desk newDesk = deskService.getById(editTaskRequest.getDeskId());
      task.setDesk(newDesk);
    }
    if (editTaskRequest.getName() != null) {
      task.setName(editTaskRequest.getName());
    }
    if (editTaskRequest.getDescription() != null) {
      task.setDescription(editTaskRequest.getDescription());
    }
    if (editTaskRequest.getCompleted() != null) {
      task.setCompleted(editTaskRequest.getCompleted());
    }

    return update(task);
  }

  @Override
  public Task update(Task task) {

    return taskRepository.save(task);
  }

  @Override
  public void deleteById(Long id) {

    taskRepository.deleteById(id);
  }

}
