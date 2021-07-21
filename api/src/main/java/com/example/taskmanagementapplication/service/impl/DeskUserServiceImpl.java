package com.example.taskmanagementapplication.service.impl;

import com.example.taskmanagementapplication.entity.Desk;
import com.example.taskmanagementapplication.entity.DeskUser;
import com.example.taskmanagementapplication.entity.User;
import com.example.taskmanagementapplication.enumeration.ErrorTypeEnum;
import com.example.taskmanagementapplication.exception.CustomException;
import com.example.taskmanagementapplication.repository.DeskUserRepository;
import com.example.taskmanagementapplication.service.DeskService;
import com.example.taskmanagementapplication.service.DeskUserService;
import com.example.taskmanagementapplication.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.lang.String.format;

@Log4j2
@Service
@RequiredArgsConstructor
public class DeskUserServiceImpl implements DeskUserService {

  private final DeskUserRepository deskUserRepository;
  private final DeskService deskService;
  private final UserService userService;

  @Override
  public DeskUser create(Long deskId, Long userId) {

    return create(deskId, userId, false);
  }

  @Override
  public DeskUser create(Long deskId, Long userId, Boolean owner) {
    Desk desk = deskService.get(deskId);
    User user = userService.get(userId);
    DeskUser deskUser = DeskUser.builder()
        .desk(desk)
        .user(user)
        .owner(owner)
        .build();

    return update(deskUser);
  }

  @Override
  public DeskUser get(Long deskId, Long userId) {
    deskService.get(deskId);
    userService.get(userId);
    Optional<DeskUser> deskUser = deskUserRepository.findByDesk_IdAndUser_Id(deskId, userId);
    if (deskUser.isEmpty()) {
      throw new CustomException(ErrorTypeEnum.NOT_FOUND, format("There is no user with id '%s' attached to the desk with id '%s'", userId, deskId));
    }
    return deskUser.get();
  }

  @Override
  public List<DeskUser> getAllByUserId(Long userId) {

    return deskUserRepository.getAllByUser_Id(userId);
  }

  @Override
  public List<DeskUser> getAllByDeskId(Long deskId) {

    return deskUserRepository.getAllByDesk_Id(deskId);
  }

  @Override
  public DeskUser update(DeskUser deskUser) {

    return deskUserRepository.save(deskUser);
  }

  @Override
  public void delete(Long deskId, Long userId) {
    DeskUser deskUser = get(deskId, userId);
    delete(deskUser);
  }

  @Override
  public void delete(DeskUser deskUser) {

    deskUserRepository.delete(deskUser);
  }

}
