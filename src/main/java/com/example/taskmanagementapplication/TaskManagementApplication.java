package com.example.taskmanagementapplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

@SpringBootApplication
//@EnableSwagger2WebMvc
public class TaskManagementApplication {

  public static void main(String[] args) {
    SpringApplication.run(TaskManagementApplication.class, args);
  }

}
