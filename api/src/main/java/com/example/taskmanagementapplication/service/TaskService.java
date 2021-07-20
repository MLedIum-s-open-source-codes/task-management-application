package com.example.taskmanagementapplication.service;

import com.example.taskmanagementapplication.domain.dto.TaskDto;
import com.example.taskmanagementapplication.entity.Task;

import java.util.List;

public interface TaskService {

  Task create(TaskDto taskDto, Long userId);

  Task getByIdAndUserId(Long id, Long userId);

  Task getById(Long id);

  List<Task> getByDeskIdAndUserId(Long deskId, Long userId);

  List<Task> getByDeskId(Long deskId);

  Task edit(TaskDto taskDto, Long userId);

  Task edit(TaskDto taskDto);

  Task update(Task task);

  void deleteByIdAndUserId(Long id, Long userId);

  void deleteById(Long id);

}
