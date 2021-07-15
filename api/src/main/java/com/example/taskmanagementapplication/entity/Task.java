package com.example.taskmanagementapplication.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "/tasks")
public class Task {

  @Id
  private Long id;

  private String name;

  private String description;

  private boolean isCompleted;

  @ManyToOne
  private Desk desk;

  @OneToMany
  private Set<Subtask> subtasks;

}
