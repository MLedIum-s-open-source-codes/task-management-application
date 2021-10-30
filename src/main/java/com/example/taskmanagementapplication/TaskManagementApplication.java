package com.example.taskmanagementapplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

@SpringBootApplication
@EnableJpaAuditing
public class TaskManagementApplication {

  public static void main(String[] args) {
    SpringApplication.run(TaskManagementApplication.class, args);
  }

}
