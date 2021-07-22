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
  @OneToMany(mappedBy = "desk",
      fetch = FetchType.EAGER,
      cascade = CascadeType.ALL,
      orphanRemoval = true)
  @EqualsAndHashCode.Exclude
  private Set<DeskUser> deskUsers = new HashSet<>();

  @Builder.Default
  @OneToMany(mappedBy = "desk",
      fetch = FetchType.EAGER,
      cascade = CascadeType.ALL,
      orphanRemoval = true)
  @EqualsAndHashCode.Exclude
  private Set<Task> tasks = new HashSet<>();

}
