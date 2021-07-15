package com.example.taskmanagementapplication.service;

import com.example.taskmanagementapplication.entity.Subtask;

public interface SubtaskService {

  Subtask getById(Long id);

  Subtask update(Subtask subtask);

}
