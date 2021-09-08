package com.example.taskmanagementapplication.service;

import com.example.taskmanagementapplication.entity.TaskUser;

import java.util.List;

public interface TaskUserService {

  TaskUser create(Long taskId, Long userId);

  TaskUser get(Long taskId, Long userId);

  List<TaskUser> getAllByTaskId(Long id);

  List<TaskUser> getAllByUserId(Long id);

  TaskUser update(TaskUser taskUser);

  void delete(Long taskId, Long userId);

}
