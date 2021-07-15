package com.example.taskmanagementapplication.service;

import com.example.taskmanagementapplication.entity.Desk;

public interface DeskService {

  Desk getById(Long id);

  Desk update(Desk desk);

}
