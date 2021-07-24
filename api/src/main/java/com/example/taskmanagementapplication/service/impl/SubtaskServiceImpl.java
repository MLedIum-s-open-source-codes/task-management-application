package com.example.taskmanagementapplication.service.impl;

import com.example.taskmanagementapplication.domain.dto.SubtaskDto;
import com.example.taskmanagementapplication.entity.Subtask;
import com.example.taskmanagementapplication.entity.Task;
import com.example.taskmanagementapplication.enumeration.ErrorTypeEnum;
import com.example.taskmanagementapplication.exception.CustomException;
import com.example.taskmanagementapplication.repository.SubtaskRepository;
import com.example.taskmanagementapplication.service.SubtaskService;
import com.example.taskmanagementapplication.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    Optional<Subtask> subtaskOptional = subtaskRepository.findById(id);
    if (subtaskOptional.isEmpty()) {
      throw new CustomException(ErrorTypeEnum.NOT_FOUND, format("Subtask with id '%s' was not found", id));
    }
    return subtaskOptional.get();
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
    Subtask subtask = get(id);
    subtask.getTask().getSubtasks().remove(subtask);
    subtaskRepository.deleteById(id);
  }
}
