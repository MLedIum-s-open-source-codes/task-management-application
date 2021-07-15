package com.example.taskmanagementapplication.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Data
@Entity
public class Subtask {

  @Id
  private Long id;

  private String description;

  private boolean isCompleted;

  @ManyToOne
  private Task task;
}
