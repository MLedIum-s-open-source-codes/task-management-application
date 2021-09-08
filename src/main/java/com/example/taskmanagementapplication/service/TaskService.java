package com.example.taskmanagementapplication.service;

import com.example.taskmanagementapplication.domain.dto.TaskDto;
import com.example.taskmanagementapplication.entity.Task;

import java.util.List;

public interface TaskService {

  Task create(TaskDto dto);

  Task get(Long id);

  List<Task> getAllByDeskId(Long id);

  Task edit(TaskDto dto);

  void checkExistsTaskWithId(Long id);

  Task update(Task task);

  void delete(Long id);

}
