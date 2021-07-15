package com.example.taskmanagementapplication.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.Set;

@Data
@Entity
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
