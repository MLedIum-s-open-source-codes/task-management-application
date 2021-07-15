package com.example.taskmanagementapplication.domain.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EditDeskRequest {

  private Long id;

  private String name;

  private String description;

  private Long userId;

  private Long removeUserId;

}
