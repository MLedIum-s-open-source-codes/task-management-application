package com.example.taskmanagementapplication.security;

import com.example.taskmanagementapplication.entity.User;
import com.example.taskmanagementapplication.enumeration.ErrorTypeEnum;
import com.example.taskmanagementapplication.exception.CustomException;
import com.example.taskmanagementapplication.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;

@Log4j2
@Service("userDetailsService")
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

  private final UserService userService;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userService.getByUsername(username);
    if (!user.getIsActive()) {
      throw new CustomException(
          ErrorTypeEnum.ACCESS_DENIED,
          format("User with username '%s' is not active", username)
      );
    }
    log.info("User '{}' authorize successfully", user.getUsername());

    List<GrantedAuthority> grantedAuthorities = user.getRoles().stream()
        .map(role -> "ROLE_" + role.getName().toUpperCase())
        .map(SimpleGrantedAuthority::new)
        .collect(Collectors.toList());

    return UserDetailsImpl.builder()
        .id(user.getId())
        .username(user.getUsername())
        .password(user.getPassword())
        .enabled(user.getIsActive())
        .authorities(grantedAuthorities)
        .build();
  }

}
