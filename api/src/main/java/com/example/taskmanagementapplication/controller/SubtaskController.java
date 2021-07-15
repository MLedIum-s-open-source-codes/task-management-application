package com.example.taskmanagementapplication.controller;

import com.example.taskmanagementapplication.service.SubtaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/subtask")
@RequiredArgsConstructor
public class SubtaskController {

  private final SubtaskService subtaskService;

}
