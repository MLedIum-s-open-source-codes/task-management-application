package com.example.taskmanagementapplication.service;

import com.example.taskmanagementapplication.domain.request.EditSubtaskRequest;
import com.example.taskmanagementapplication.entity.Subtask;

import java.util.List;

public interface SubtaskService {

  Subtask create(EditSubtaskRequest editSubtaskRequest);

  Subtask getById(Long id);

  List<Subtask> getByTaskId(Long taskId);

  Subtask edit(EditSubtaskRequest editSubtaskRequest);

  Subtask update(Subtask subtask);

  void deleteById(Long id);

}
