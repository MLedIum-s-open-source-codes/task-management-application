package com.example.taskmanagementapplication.service;

import com.example.taskmanagementapplication.domain.dto.DeskDto;
import com.example.taskmanagementapplication.entity.Desk;

import java.util.List;

public interface DeskService {

  Desk create(DeskDto deskDto);

  Desk get(Long id);

  Desk edit(DeskDto deskDto);

  Desk update(Desk desk);

  void delete(Long id);

}
