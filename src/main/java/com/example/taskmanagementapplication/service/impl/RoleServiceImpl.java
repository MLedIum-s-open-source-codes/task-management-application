package com.example.taskmanagementapplication.service.impl;

import com.example.taskmanagementapplication.entity.Role;
import com.example.taskmanagementapplication.enumeration.ErrorTypeEnum;
import com.example.taskmanagementapplication.exception.CustomException;
import com.example.taskmanagementapplication.repository.RoleRepository;
import com.example.taskmanagementapplication.service.RoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Log4j2
@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

  private final RoleRepository roleRepository;

  @Override
  public Role getRole(Long roleId) {
    Optional<Role> role = roleRepository.findById(roleId);
    if (role.isPresent())
        return role.get();


    log.error("Role with id '{}' was not found", roleId);
    throw new CustomException(
        ErrorTypeEnum.NOT_FOUND,
        String.format("Role with id '%s' was not found", roleId)
    );
  }

  @Override
  public Role getRole(String name) {
    Optional<Role> role = roleRepository.findByNameIgnoreCase(name);
    if (role.isPresent()) {
      return role.get();
    }
    log.error("Role with name '{}' was not found", name);
    throw new CustomException(
        ErrorTypeEnum.NOT_FOUND,
        String.format("Role with name '%s' was not found", name)
    );
  }

  @Override
  public List<Role> getAllRoles() {

    return roleRepository.findAll();
  }

}
