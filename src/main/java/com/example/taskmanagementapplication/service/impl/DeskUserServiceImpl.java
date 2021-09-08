package com.example.taskmanagementapplication.service.impl;

import com.example.taskmanagementapplication.entity.Desk;
import com.example.taskmanagementapplication.entity.DeskUser;
import com.example.taskmanagementapplication.entity.Task;
import com.example.taskmanagementapplication.entity.User;
import com.example.taskmanagementapplication.enumeration.ErrorTypeEnum;
import com.example.taskmanagementapplication.exception.CustomException;
import com.example.taskmanagementapplication.repository.DeskUserRepository;
import com.example.taskmanagementapplication.service.DeskService;
import com.example.taskmanagementapplication.service.DeskUserService;
import com.example.taskmanagementapplication.service.TaskUserService;
import com.example.taskmanagementapplication.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.*;

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
  public DeskUser create(Long deskId, Long userId, Boolean isOwner) {
    Desk desk = deskService.get(deskId);
    User user = userService.get(userId);
    if (deskUserRepository.existsByDesk_IdAndUser_Id(deskId, userId)) {
      throw new CustomException(ErrorTypeEnum.ALREADY_EXIST, format("User with id '%s' already attached to the desk with id '%s'", userId, deskId));
    }
    DeskUser deskUser = DeskUser.builder()
        .desk(desk)
        .user(user)
        .owner(isOwner)
        .build();
    return update(deskUser);
  }

  @Override
  public DeskUser get(Long deskId, Long userId) {
    deskService.checkExistsDeskWithId(deskId);
    userService.checkExistsUserWithId(userId);
    Optional<DeskUser> optional = deskUserRepository.findByDesk_IdAndUser_Id(deskId, userId);
    if (optional.isEmpty())
        throw new CustomException(ErrorTypeEnum.NOT_FOUND, format("There is no user with id '%s' attached to the desk with id '%s'", userId, deskId));

    return optional.get();
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
  public List<DeskUser> changeOwner(Long deskId, Long newOwnerId, Long oldOwnerId) {
    DeskUser oldOnwer = get(deskId, oldOwnerId);
    oldOnwer.setOwner(false);

    DeskUser newOwner = get(deskId, newOwnerId);
    newOwner.setOwner(true);

    deskUserRepository.saveAll(Arrays.asList(newOwner, oldOnwer));

    return getAllByDeskId(deskId);
  }

  @Override
  public void checkContainsDeskWithIdUserWithId(Long deskId, Long userId) {
    if (!containsDeskWithIdUserWithId(deskId, userId))
        throw new CustomException(ErrorTypeEnum.ACCESS_DENIED, format("User with id '%s' hasn't access to desk with id '%s'", userId, deskId));
  }

  @Override
  public void checkIsDeskOwner(Long deskId, Long userId) {
    if (!isDeskOwner(deskId, userId))
        throw new CustomException(ErrorTypeEnum.ACCESS_DENIED, format("User with id '%s' hasn't access to this action", userId));
  }

  private boolean containsDeskWithIdUserWithId(Long deskId, Long userId) {

    return deskUserRepository.existsByDesk_IdAndUser_Id(deskId, userId);
  }

  private boolean isDeskOwner(Long deskId, Long userId) {

    return get(deskId, userId).getOwner();
  }

  @Override
  public DeskUser update(DeskUser deskUser) {

    return deskUserRepository.save(deskUser);
  }

  @Override
  public void delete(Long deskId, Long userId) {
    checkContainsDeskWithIdUserWithId(deskId, userId);

    deskUserRepository.delete(get(deskId, userId));
    //deskUserRepository.deleteByDesk_IdAndUser_Id(deskId, userId);
  }

}
