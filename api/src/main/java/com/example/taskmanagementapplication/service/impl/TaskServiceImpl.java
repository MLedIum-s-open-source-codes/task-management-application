package com.example.taskmanagementapplication.service.impl;

import com.example.taskmanagementapplication.domain.dto.TaskDto;
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
  public Task create(TaskDto taskDto, Long userId) {
    Task task = Task.builder()
        .name(taskDto.getName())
        .description(taskDto.getDescription())
        .completed(taskDto.getCompleted())
        .build();

    Desk desk = deskService.getById(taskDto.getDeskId());
    desk.getTasks().add(task);
    task.setDesk(desk);

    return update(task);
  }

  @Override
  public Task getByIdAndUserId(Long id, Long userId) {
    return null;
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
  public List<Task> getByDeskIdAndUserId(Long deskId, Long userId) {
    return null;
  }

  @Override
  public List<Task> getByDeskId(Long deskId) {

    return taskRepository.findAllByDeskId(deskId);
  }

  @Override
  public Task edit(TaskDto taskDto, Long userId) {
    return null;
  }

  @Override
  public Task edit(TaskDto taskDto) {
    Task task = getById(taskDto.getId());


    if (taskDto.getDeskId() != null) {
      Desk oldDesk = task.getDesk();
      oldDesk.getTasks().remove(task);
      Desk newDesk = deskService.getById(taskDto.getDeskId());
      task.setDesk(newDesk);
    }
    if (taskDto.getName() != null) {
      task.setName(taskDto.getName());
    }
    if (taskDto.getDescription() != null) {
      task.setDescription(taskDto.getDescription());
    }
    if (taskDto.getCompleted() != null) {
      task.setCompleted(taskDto.getCompleted());
    }

    return update(task);
  }

  @Override
  public Task updateAndUserId(Task task, Long userId) {
    return null;
  }

  @Override
  public Task update(Task task) {

    return taskRepository.save(task);
  }

  @Override
  public void deleteByIdAndUserId(Long id, Long userId) {

  }

  @Override
  public void deleteById(Long id) {

    taskRepository.deleteById(id);
  }

}
