package com.example.taskmanagementapplication.service.impl;

import com.example.taskmanagementapplication.domain.request.EditSubtaskRequest;
import com.example.taskmanagementapplication.entity.Subtask;
import com.example.taskmanagementapplication.repository.SubtaskRepository;
import com.example.taskmanagementapplication.service.SubtaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class SubtaskServiceImpl implements SubtaskService {

  private final SubtaskRepository subtaskRepository;

  @Override
  public Subtask create(EditSubtaskRequest editSubtaskRequest) {

    return null;
  }

  @Override
  public Subtask getById(Long id) {
    Subtask subtask = subtaskRepository.getById(id);
    if (subtask == null) {
      throw new RuntimeException(format("Subtask with id '%s' was not found", id));
    }
    return subtask;
  }

  @Override
  public List<Subtask> getByTaskId(Long taskId) {

    return subtaskRepository.findAllByTaskId(taskId);
  }

  @Override
  public Subtask edit(EditSubtaskRequest editSubtaskRequest) {

    return null;
  }

  @Override
  public Subtask update(Subtask subtask) {

    return subtaskRepository.save(subtask);
  }

  @Override
  public void deleteById(Long id) {

    subtaskRepository.deleteById(id);
  }

}
