package com.example.taskmanagementapplication.controller;

import com.example.taskmanagementapplication.service.DeskService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/desk")
@RequiredArgsConstructor
public class DeskController {

  private final DeskService deskService;

}
