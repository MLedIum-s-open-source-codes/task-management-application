package com.example.taskmanagementapplication.enumeration;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
public enum RoleEnum {

  ADMIN("ADMIN", "Администратор"),
  USER("USER", "Пользователь");

  private final String name;
  private final String description;

  RoleEnum(String name, String description) {
    this.name = name;
    this.description = description;
  }

  public static List<RoleEnum> getRoles() {
    return Arrays.asList(RoleEnum.values());
  }

  public static boolean isExist(String name) {
    return getRoles().contains(RoleEnum.valueOf(name));
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

}
