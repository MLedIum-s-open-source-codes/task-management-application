package com.example.taskmanagementapplication.service.impl;

import com.example.taskmanagementapplication.domain.dto.SubtaskDto;
import com.example.taskmanagementapplication.entity.Subtask;
import com.example.taskmanagementapplication.entity.Task;
import com.example.taskmanagementapplication.repository.SubtaskRepository;
import com.example.taskmanagementapplication.service.SubtaskService;
import com.example.taskmanagementapplication.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class SubtaskServiceImpl implements SubtaskService {

  private final SubtaskRepository subtaskRepository;
  private final TaskService taskService;


  @Override
  public Subtask create(SubtaskDto subtaskDto) {
    Task task = taskService.get(subtaskDto.getTaskId());
    subtaskDto.setId(null);
    return update(subtaskDto.toDomain(task));
  }

  @Override
  public Subtask get(Long id) {

    return null;
  }

  @Override
  public List<Subtask> getAllByTaskId(Long taskId) {

    return null;
  }

  @Override
  public Subtask edit(SubtaskDto subtaskDto) {

    return null;
  }

  @Override
  public Subtask update(Subtask subtask) {

    return null;
  }

  @Override
  public void delete(Long id) {

  }
}
