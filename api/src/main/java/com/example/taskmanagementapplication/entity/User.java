package com.example.taskmanagementapplication.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.Set;

@Data
@Entity
public class User {

  @Id
  private Long id;

  private String username;

  @ManyToMany
  private Set<Desk> desks;
}
