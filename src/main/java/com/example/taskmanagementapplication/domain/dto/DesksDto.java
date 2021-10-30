package com.example.taskmanagementapplication.domain.dto;

import com.example.taskmanagementapplication.entity.Desk;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class DesksDto {

  private List<DeskDto> desks;

  public DesksDto(List<Desk> desks) {

    this.desks = desks == null ? null : desks.stream().map(DeskDto::of).collect(Collectors.toList());
  }

}
