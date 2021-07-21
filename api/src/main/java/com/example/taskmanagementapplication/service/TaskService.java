package com.example.taskmanagementapplication.service;

import com.example.taskmanagementapplication.domain.dto.SubtaskDto;
import com.example.taskmanagementapplication.domain.dto.TaskDto;
import com.example.taskmanagementapplication.entity.Subtask;
import com.example.taskmanagementapplication.entity.Task;

import java.util.List;

public interface TaskService {

  Task create(TaskDto taskDto);

  Task get(Long id);

  List<Task> getAllByDeskId(Long deskId);

  Task edit(TaskDto taskDto);

  Task update(Task task);

  void delete(Long id);

}
