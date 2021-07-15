package com.example.taskmanagementapplication.domain.dto;

import com.example.taskmanagementapplication.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

  private Long id;

  private String username;

  //private DesksDto desks;

  public static UserDto of(User user) {
    return UserDto.builder()
        .id(user.getId())
        .username(user.getUsername())
        //.desks(new DesksDto(user.getDesks().stream().toList()))
        .build();
  }

}
