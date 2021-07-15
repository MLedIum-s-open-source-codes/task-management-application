package com.example.taskmanagementapplication.domain.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EditTaskRequest {

  private Long id;

  private String name;

  private String description;

  private Boolean completed;

  private Long deskId;

}
