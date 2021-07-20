package com.example.taskmanagementapplication.security;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailsImpl implements UserDetails {

  private Long id;
  private String username;
  private String password;
  private List<GrantedAuthority> authorities;
  @Builder.Default
  private boolean accountNonExpired = true;
  @Builder.Default
  private boolean accountNonLocked = true;
  @Builder.Default
  private boolean credentialsNonExpired = true;
  @Builder.Default
  private boolean enabled = false;
  private Map<String, Object> attributes;

}
