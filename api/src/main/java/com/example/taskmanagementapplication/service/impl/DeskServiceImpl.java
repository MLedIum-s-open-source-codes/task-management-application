package com.example.taskmanagementapplication.service.impl;

import com.example.taskmanagementapplication.entity.Desk;
import com.example.taskmanagementapplication.repository.DeskRepository;
import com.example.taskmanagementapplication.service.DeskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class DeskServiceImpl implements DeskService {

  private final DeskRepository deskRepository;

  @Override
  public Desk getById(Long id) {
    Desk desk = deskRepository.getById(id);
    if (desk == null) {
      throw new RuntimeException(format("Desk with id '%s' was not found", id));
    }
    return desk;
  }

  @Override
  public Desk update(Desk desk) {
    return deskRepository.save(desk);
  }

}
