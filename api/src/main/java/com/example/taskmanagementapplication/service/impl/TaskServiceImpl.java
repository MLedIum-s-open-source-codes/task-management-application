package com.example.taskmanagementapplication.service.impl;

import com.example.taskmanagementapplication.entity.Task;
import com.example.taskmanagementapplication.repository.TaskRepository;
import com.example.taskmanagementapplication.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

  private final TaskRepository taskRepository;

  @Override
  public Task getById(Long id) {
    Task task = taskRepository.getById(id);
    if (task == null) {
      throw new RuntimeException(format("Task with id '%s' was not found", id));
    }
    return task;
  }

  @Override
  public Task update(Task task) {
    return taskRepository.save(task);
  }

}
