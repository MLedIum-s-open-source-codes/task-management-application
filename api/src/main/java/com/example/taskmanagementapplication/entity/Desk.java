package com.example.taskmanagementapplication.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.Set;

@Data
@Entity
public class Desk {

  @Id
  private Long id;

  private String name;

  private String description;

  @ManyToMany
  private Set<User> users;

  @OneToMany
  private Set<Task> tasks;

}
