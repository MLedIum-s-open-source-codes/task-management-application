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
@Table(name = "tasks")
public class Task {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  private String description;

  private Boolean completed;

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

}
