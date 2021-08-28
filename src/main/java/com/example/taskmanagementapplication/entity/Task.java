package com.example.taskmanagementapplication.entity;

import lombok.*;

import javax.persistence.*;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tasks")
public class Task {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  private String note;

  private Boolean completed;

  private Boolean important;

  private Instant completionDate;

  @ManyToOne
  @JoinColumn(name = "desk_id",
      referencedColumnName = "id")
  private Desk desk;

  @Builder.Default
  @OneToMany(mappedBy = "task",
      fetch = FetchType.EAGER,
      cascade = CascadeType.ALL,
      orphanRemoval = true)
  @EqualsAndHashCode.Exclude
  private Set<Subtask> subtasks = new HashSet<>();

  @Builder.Default
  @EqualsAndHashCode.Exclude
  @ManyToMany(cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER)
  @JoinTable(name = "tasks_users",
      joinColumns = @JoinColumn(name = "task_id",
          referencedColumnName = "id"),
      inverseJoinColumns = @JoinColumn(name = "user_id",
          referencedColumnName = "id"))
  private Set<User> users = new HashSet<>();

}
