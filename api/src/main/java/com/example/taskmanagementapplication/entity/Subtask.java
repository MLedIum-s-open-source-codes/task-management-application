package com.example.taskmanagementapplication.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "/subtasks")
public class Subtask {

  @Id
  private Long id;

  private String description;

  private boolean isCompleted;

  @ManyToOne
  private Task task;
}
