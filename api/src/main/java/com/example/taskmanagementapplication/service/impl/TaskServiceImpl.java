package com.example.taskmanagementapplication.service.impl;

import com.example.taskmanagementapplication.domain.dto.TaskDto;
import com.example.taskmanagementapplication.entity.Desk;
import com.example.taskmanagementapplication.entity.Task;
import com.example.taskmanagementapplication.enumeration.ErrorTypeEnum;
import com.example.taskmanagementapplication.exception.CustomException;
import com.example.taskmanagementapplication.repository.TaskRepository;
import com.example.taskmanagementapplication.service.DeskService;
import com.example.taskmanagementapplication.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

  private final TaskRepository taskRepository;
  private final DeskService deskService;


  @Override
  public Task create(TaskDto taskDto) {
    Desk desk = deskService.get(taskDto.getDeskId());
    taskDto.setId(null);
    return update(taskDto.toDomain(desk));
  }

  @Override
  public Task get(Long id) {
    Optional<Task> taskOptional = taskRepository.findById(id);
    if (taskOptional.isEmpty()) {
      throw new CustomException(ErrorTypeEnum.NOT_FOUND, format("Task with id '%s' was not found", id));
    }
    return taskOptional.get();
  }

  @Override
  public List<Task> getAllByDeskId(Long deskId) {

    return taskRepository.findAllByDeskId(deskId);
  }

  @Override
  public Task edit(TaskDto taskDto) {
    Task task = taskRepository.getById(taskDto.getId());

    if (taskDto.getName() != null) {
      task.setName(taskDto.getName());
    }
    if (taskDto.getNote() != null) {
      task.setNote(taskDto.getNote());
    }
    if (taskDto.getCompleted() != null) {
      task.setCompleted(taskDto.getCompleted());
    }
    if (taskDto.getImportant() != null) {
      task.setImportant(taskDto.getImportant());
    }
    if (taskDto.getCompletionDate() != null) {
      task.setCompletionDate(taskDto.getCompletionDate());
    }

    return update(task);
  }

  @Override
  public Task update(Task task) {

    return taskRepository.save(task);
  }

  @Override
  public void delete(Long id) {
    Task task = get(id);
    task.getDesk().getTasks().remove(task);
    taskRepository.deleteById(id);
  }
}
