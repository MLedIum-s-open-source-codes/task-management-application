package com.example.taskmanagementapplication.service.impl;

import com.example.taskmanagementapplication.domain.request.EditDeskRequest;
import com.example.taskmanagementapplication.entity.Desk;
import com.example.taskmanagementapplication.repository.DeskRepository;
import com.example.taskmanagementapplication.service.DeskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class DeskServiceImpl implements DeskService {

  private final DeskRepository deskRepository;

  @Override
  public Desk create(EditDeskRequest editDeskRequest) {

    return null;
  }

  @Override
  public Desk getById(Long id) {
    Desk desk = deskRepository.getById(id);
    if (desk == null) {
      throw new RuntimeException(format("Desk with id '%s' was not found", id));
    }
    return desk;
  }

  @Override
  public List<Desk> getByUserId(Long userId) {

    return deskRepository.findAllByUserId(userId);
  }

  @Override
  public Desk edit(EditDeskRequest editDeskRequest) {

    return null;
  }

  @Override
  public Desk update(Desk desk) {

    return deskRepository.save(desk);
  }

  @Override
  public void deleteById(Long id) {

    deskRepository.deleteById(id);
  }

}
