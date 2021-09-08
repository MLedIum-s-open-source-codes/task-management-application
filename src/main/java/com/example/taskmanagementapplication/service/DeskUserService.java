package com.example.taskmanagementapplication.service;

import com.example.taskmanagementapplication.entity.DeskUser;

import java.util.List;

public interface DeskUserService {

  DeskUser create(Long deskId, Long userId);

  DeskUser create(Long deskId, Long userId, Boolean owner);

  DeskUser get(Long deskId, Long userId);

  List<DeskUser> getAllByUserId(Long id);

  List<DeskUser> getAllByDeskId(Long id);

  List<DeskUser> changeOwner(Long deskId, Long newOwnerId, Long oldOwnerId);

  void checkContainsDeskWithIdUserWithId(Long deskId, Long userId);

  void checkIsDeskOwner(Long deskId, Long userId);

  DeskUser update(DeskUser deskUser);

  void delete(Long deskId, Long userId);

}
