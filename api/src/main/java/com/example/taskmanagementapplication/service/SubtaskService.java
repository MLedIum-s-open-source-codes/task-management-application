package com.example.taskmanagementapplication.service;

import com.example.taskmanagementapplication.domain.dto.DeskDto;
import com.example.taskmanagementapplication.domain.dto.SubtaskDto;
import com.example.taskmanagementapplication.entity.Desk;
import com.example.taskmanagementapplication.entity.Subtask;

import java.util.List;

public interface SubtaskService {

  Subtask create(SubtaskDto subtaskDto);

  Subtask get(Long id);

  List<Subtask> getAllByTaskId(Long taskId);

  Subtask edit(SubtaskDto subtaskDto);

  Subtask update(Subtask subtask);

  void delete(Long id);

}
