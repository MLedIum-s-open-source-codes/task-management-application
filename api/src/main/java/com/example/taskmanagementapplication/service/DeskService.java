package com.example.taskmanagementapplication.service;

import com.example.taskmanagementapplication.domain.request.EditDeskRequest;
import com.example.taskmanagementapplication.entity.Desk;

import java.util.List;

public interface DeskService {

  Desk create(EditDeskRequest editDeskRequest);

  Desk getById(Long id);

  List<Desk> getByUserId(Long userId);

  Desk edit(EditDeskRequest editDeskRequest);

  Desk update(Desk desk);

  void deleteById(Long id);

}
