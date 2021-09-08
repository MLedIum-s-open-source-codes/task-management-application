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
  public Task create(TaskDto dto) {
    Desk desk = deskService.get(dto.getDeskId());
    dto.setId(null);
    return update(dto.toDomain(desk));
  }

  @Override
  public Task get(Long id) {
    Optional<Task> optional = taskRepository.findById(id);
    if (optional.isEmpty()) {
      throw new CustomException(ErrorTypeEnum.NOT_FOUND, format("Task with id '%s' was not found", id));
    }
    return optional.get();
  }

  @Override
  public List<Task> getAllByDeskId(Long deskId) {

    return taskRepository.findAllByDeskId(deskId);
  }

  @Override
  public Task edit(TaskDto dto) {
    Task task = taskRepository.getById(dto.getId());

    if (dto.getName() != null) {
      task.setName(dto.getName());
    }
    if (dto.getNote() != null) {
      task.setNote(dto.getNote());
    }
    if (dto.getCompleted() != null) {
      task.setCompleted(dto.getCompleted());
    }
    if (dto.getImportant() != null) {
      task.setImportant(dto.getImportant());
    }
    if (dto.getCompleteBeforeDate() != null) {
      task.setCompleteBeforeDate(dto.getCompleteBeforeDate());
    }

    return update(task);
  }

  @Override
  public void checkExistsTaskWithId(Long id) {
    if (!existsTaskWithId(id))
        throw new CustomException(ErrorTypeEnum.NOT_FOUND, format("Task with id '%s' was not found", id));
  }

  private boolean existsTaskWithId(Long id) {

    return taskRepository.existsById(id);
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
