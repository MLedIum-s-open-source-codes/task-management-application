package com.example.taskmanagementapplication.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "roles")
public class Role {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  private String description;

  @JsonIgnore
  @Builder.Default
  @EqualsAndHashCode.Exclude
  @ManyToMany(mappedBy = "roles",
      fetch = FetchType.LAZY)
  @Fetch(value = FetchMode.SUBSELECT)
  private Set<User> users = new HashSet<>();

  public void addUser(User user) {
    if (!this.users.contains(user)) {
      this.users.add(user);
      user.addRole(this);
    }
  }

  public void removeUser(User user) {
    if (this.users.contains(user)) {
      this.users.remove(user);
      user.removeRole(this);
    }
  }

}