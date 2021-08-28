package com.example.taskmanagementapplication.service;

import com.example.taskmanagementapplication.entity.Role;

import java.util.List;

public interface RoleService {

  Role getRole(Long roleId);

  Role getRole(String name);

  List<Role> getAllRoles();

}
