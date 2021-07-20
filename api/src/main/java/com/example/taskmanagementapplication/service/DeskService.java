package com.example.taskmanagementapplication.service;

import com.example.taskmanagementapplication.domain.dto.DeskDto;
import com.example.taskmanagementapplication.entity.Desk;

import java.util.List;

public interface DeskService {

  Desk create(DeskDto deskDto, Long userId);

  Desk getById(Long id);

  List<Desk> getByUserId(Long userId);

  Desk edit(DeskDto deskDto, Long userId);

  Desk update(Desk desk);

  void deleteById(Long id);

}
