package com.example.taskmanagementapplication.service;

import com.example.taskmanagementapplication.domain.dto.SubtaskDto;
import com.example.taskmanagementapplication.entity.Subtask;

import java.util.List;

public interface SubtaskService {

  Subtask create(SubtaskDto subtaskDto);

  Subtask getByIdAndUserId(Long id, Long userId);

  Subtask getById(Long id);

  List<Subtask> getAllByTaskIdAndUserId(Long taskId, Long userId);

  List<Subtask> getAllByTaskId(Long taskId);

  Subtask editWithUserId(SubtaskDto subtaskDto, Long userId);

  Subtask edit(SubtaskDto subtaskDto);

  Subtask update(Subtask subtask);

  void hideByIdAndUserId(Long id, Long userId);

  void deleteById(Long id);

}
