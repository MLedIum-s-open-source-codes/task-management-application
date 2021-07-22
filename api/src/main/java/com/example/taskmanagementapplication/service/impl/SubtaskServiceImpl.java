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

    return subtaskRepository.getById(id);
  }

  @Override
  public List<Subtask> getAllByTaskId(Long taskId) {

    return subtaskRepository.getAllByTaskId(taskId);
  }

  @Override
  public Subtask edit(SubtaskDto subtaskDto) {
    Subtask subtask = get(subtaskDto.getId());

    if (subtaskDto.getDescription() != null) {
      subtask.setDescription(subtaskDto.getDescription());
    }
    if (subtaskDto.getCompleted() != null) {
      subtask.setCompleted(subtaskDto.getCompleted());
    }

    return update(subtask);
  }

  @Override
  public Subtask update(Subtask subtask) {

    return subtaskRepository.save(subtask);
  }

  @Override
  public void delete(Long id) {

    subtaskRepository.deleteById(id);
  }
}
