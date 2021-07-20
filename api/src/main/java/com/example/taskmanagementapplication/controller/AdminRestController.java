package com.example.taskmanagementapplication.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Secured({"ROLE_ADMIN"})
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminRestController {
}
