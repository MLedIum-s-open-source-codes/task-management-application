package com.example.taskmanagementapplication.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "subtasks")
public class Subtask {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String description;

  private boolean completed;

  @ManyToOne
  @JoinColumn(name = "task_id",
      referencedColumnName = "id")
  private Task task;
}
