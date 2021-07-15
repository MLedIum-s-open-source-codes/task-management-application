package com.example.taskmanagementapplication.service;

import com.example.taskmanagementapplication.domain.request.EditTaskRequest;
import com.example.taskmanagementapplication.entity.Task;

import java.util.List;

public interface TaskService {

  Task create(EditTaskRequest editTaskRequest);

  Task getById(Long id);

  List<Task> getByDeskId(Long deskId);

  Task edit(EditTaskRequest editTaskRequest);

  Task update(Task task);

  void deleteById(Long id);

}
