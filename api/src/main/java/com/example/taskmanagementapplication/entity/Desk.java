package com.example.taskmanagementapplication.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "/desks")
public class Desk {

  @Id
  private Long id;

  private String name;

  private String description;

  @Builder.Default
  @ManyToMany
  private Set<User> users = new HashSet<>();

  @Builder.Default
  @OneToMany
  private Set<Task> tasks = new HashSet<>();

}
