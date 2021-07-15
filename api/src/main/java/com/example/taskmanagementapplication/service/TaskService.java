package com.example.taskmanagementapplication.service;

import com.example.taskmanagementapplication.entity.Task;

public interface TaskService {

  Task getById(Long id);

  Task update(Task task);

}
