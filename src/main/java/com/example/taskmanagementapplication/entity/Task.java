package com.example.taskmanagementapplication.entity;

import com.example.taskmanagementapplication.entity.audit.BaseEntity;
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
public class Task extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  private String note;

  private Boolean completed;

  private Boolean important;

  private Instant completeBeforeDate;

  @ManyToOne
  @JoinColumn(name = "desk_id",
      referencedColumnName = "id")
  private Desk desk;

  @Builder.Default
  private Boolean isActive = true;

  @Builder.Default
  @OneToMany(mappedBy = "task",
      fetch = FetchType.EAGER,
      cascade = CascadeType.ALL,
      orphanRemoval = true)
  @EqualsAndHashCode.Exclude
  private Set<Subtask> subtasks = new HashSet<>();

  @Builder.Default
  @OneToMany(mappedBy = "task",
      fetch = FetchType.EAGER,
      orphanRemoval = true)
  @EqualsAndHashCode.Exclude
  private Set<TaskUser> taskUsers = new HashSet<>();

}
