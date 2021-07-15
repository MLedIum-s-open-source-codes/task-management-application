package com.example.taskmanagementapplication.entity;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "desks")
public class Desk {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  private String description;

  @Builder.Default
  @ManyToMany(mappedBy = "desks")
  @EqualsAndHashCode.Exclude
  private Set<User> users = new HashSet<>();

  @Builder.Default
  @OneToMany(mappedBy = "desk")
  @EqualsAndHashCode.Exclude
  private Set<Task> tasks = new HashSet<>();

}
