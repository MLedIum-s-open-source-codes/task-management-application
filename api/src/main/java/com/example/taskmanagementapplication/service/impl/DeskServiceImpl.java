package com.example.taskmanagementapplication.service.impl;

import com.example.taskmanagementapplication.domain.request.EditDeskRequest;
import com.example.taskmanagementapplication.entity.Desk;
import com.example.taskmanagementapplication.entity.User;
import com.example.taskmanagementapplication.repository.DeskRepository;
import com.example.taskmanagementapplication.service.DeskService;
import com.example.taskmanagementapplication.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.lang.String.format;

@Log4j2
@Service
@RequiredArgsConstructor
public class DeskServiceImpl implements DeskService {

  private final DeskRepository deskRepository;
  private final UserService userService;

  @Override
  public Desk create(EditDeskRequest editDeskRequest) {
    Desk desk = Desk.builder()
        .name(editDeskRequest.getName())
        .description(editDeskRequest.getDescription())
        .build();

    User user = userService.getById(editDeskRequest.getUserId());
    user.getDesks().add(desk);
    desk.getUsers().add(user);

    return update(desk);
  }

  @Override
  public Desk getById(Long id) {
    Desk desk = deskRepository.getById(id);
    if (desk == null) {
      throw new RuntimeException(format("Desk with id '%s' was not found", id));
    }
    return desk;
  }

  @Override
  public List<Desk> getByUserId(Long userId) {

    return deskRepository.findAllByUserId(userId);
  }

  @Override
  public Desk edit(EditDeskRequest editDeskRequest) {
    Desk desk = getById(editDeskRequest.getId());

    if (editDeskRequest.getName() != null) {
      desk.setName(editDeskRequest.getName());
    }
    if (editDeskRequest.getDescription() != null) {
      desk.setDescription(editDeskRequest.getDescription());
    }
    if (editDeskRequest.getUserId() != null) {
      User addingUser = userService.getById(editDeskRequest.getUserId());
      desk.getUsers().add(addingUser);
    }
    if (editDeskRequest.getRemoveUserId() != null) {
      User removingUser = userService.getById(editDeskRequest.getRemoveUserId());
      desk.getUsers().remove(removingUser);
    }

    return update(desk);
  }

  @Override
  public Desk update(Desk desk) {

    return deskRepository.save(desk);
  }

  @Override
  public void deleteById(Long id) {

    deskRepository.deleteById(id);
  }

}
