package com.example.taskmanagementapplication.service.impl;

import com.example.taskmanagementapplication.entity.Subtask;
import com.example.taskmanagementapplication.repository.SubtaskRepository;
import com.example.taskmanagementapplication.service.SubtaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class SubtaskServiceImpl implements SubtaskService {

  private final SubtaskRepository subtaskRepository;

  @Override
  public Subtask getById(Long id) {
    Subtask subtask = subtaskRepository.getById(id);
    if (subtask == null) {
      throw new RuntimeException(format("Subtask with id '%s' was not found", id));
    }
    return subtask;
  }

  @Override
  public Subtask update(Subtask subtask) {
    return subtaskRepository.save(subtask);
  }

}
