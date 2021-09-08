package com.example.taskmanagementapplication.service;

import com.example.taskmanagementapplication.domain.dto.DeskDto;
import com.example.taskmanagementapplication.entity.Desk;

public interface DeskService {

  Desk create(DeskDto dto);

  Desk get(Long id);

  Desk edit(DeskDto dto);

  void checkExistsDeskWithId(Long id);

  Desk update(Desk desk);

  void delete(Long id);

}
