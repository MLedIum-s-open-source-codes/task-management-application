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
@Table(name = "users")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String username;

  //private String password;

  @Builder.Default
  @ManyToMany
  @JoinTable(name = "users_desks",
      joinColumns = @JoinColumn(name = "user_id"),
      inverseJoinColumns = @JoinColumn(name = "desk_id"))
  @EqualsAndHashCode.Exclude
  private Set<Desk> desks = new HashSet<>();
}
