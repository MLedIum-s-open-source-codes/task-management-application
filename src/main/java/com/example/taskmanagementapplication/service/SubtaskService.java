package com.example.taskmanagementapplication.service;

import com.example.taskmanagementapplication.domain.dto.SubtaskDto;
import com.example.taskmanagementapplication.entity.Subtask;

import java.util.List;

public interface SubtaskService {

  Subtask create(SubtaskDto dto);

  Subtask get(Long id);

  List<Subtask> getAllByTaskId(Long taskId);

  Subtask edit(SubtaskDto dto);

  void checkExistsSubtaskWithId(Long id);

  Subtask update(Subtask subtask);

  void delete(Long id);

}
