package com.example.taskmanagementapplication.entity;

import com.example.taskmanagementapplication.enumeration.RoleEnum;
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

  private String password;

  @Builder.Default
  private boolean enabled = false;

  @Builder.Default
  @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, orphanRemoval = true)
  @EqualsAndHashCode.Exclude
  private Set<DeskUser> userDesks = new HashSet<>();

  @Builder.Default
  @EqualsAndHashCode.Exclude
  @ManyToMany(cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER)
  @JoinTable(name = "users_roles",
      joinColumns = @JoinColumn(name = "user_id",
          referencedColumnName = "id"),
      inverseJoinColumns = @JoinColumn(name = "role_id",
          referencedColumnName = "id"))
  private Set<Role> roles = new HashSet<>();


  public void addRole(Role role) {

    this.roles.add(role);
  }

  public void removeRole(Role role) {
    if (this.roles.contains(role)) {
      this.roles.remove(role);
      role.removeUser(this);
    }
  }

  public Role getRole(String role) {
    return getRoles().stream()
        .filter(r -> r.getName().equals(role))
        .findFirst()
        .orElse(null);
  }

  public boolean isContains(RoleEnum roleEnum) {

    return getRoles().stream().anyMatch(role -> role.getName().equals(roleEnum.name()));
  }

  public boolean isAdmin() {

    return getRoles().stream().anyMatch(r -> r.getName().equals(RoleEnum.ADMIN.name()));
  }

}
