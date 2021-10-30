package com.example.taskmanagementapplication.service.impl;

import com.example.taskmanagementapplication.entity.DeskUser;
import com.example.taskmanagementapplication.entity.Task;
import com.example.taskmanagementapplication.entity.TaskUser;
import com.example.taskmanagementapplication.entity.User;
import com.example.taskmanagementapplication.enumeration.ErrorTypeEnum;
import com.example.taskmanagementapplication.exception.CustomException;
import com.example.taskmanagementapplication.repository.TaskUserRepository;
import com.example.taskmanagementapplication.service.DeskUserService;
import com.example.taskmanagementapplication.service.TaskService;
import com.example.taskmanagementapplication.service.TaskUserService;
import com.example.taskmanagementapplication.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class TaskUserServiceImpl implements TaskUserService {

  private final DeskUserService deskUserService;
  private final TaskUserRepository taskUserRepository;
  private final TaskService taskService;
  private final UserService userService;

  @Override
  public TaskUser create(Long taskId, Long userId) {
    Task task = taskService.get(taskId);
    User user = userService.get(userId);
    if (taskUserRepository.existsByTask_IdAndUser_Id(taskId, userId)) {
      throw new CustomException(
          ErrorTypeEnum.ALREADY_EXIST,
          format("User with id '%s' has already been assigned to the task with id '%s'", userId, taskId)
      );
    }
    DeskUser deskUser = deskUserService.get(task.getDesk().getId(), userId);
    TaskUser taskUser = TaskUser.builder()
        .task(task)
        .user(user)
        .deskUser(deskUser)
        .build();
    return update(taskUser);
  }

  @Override
  public TaskUser get(Long taskId, Long userId) {
    taskService.checkExistsTaskWithId(taskId);
    userService.checkExistsUserWithId(userId);
    Optional<TaskUser> optional = taskUserRepository.findByTask_IdAndUser_Id(taskId, userId);
    if (optional.isEmpty()) {
      throw new CustomException(
          ErrorTypeEnum.NOT_FOUND,
          format("There is no user with id '%s' assigned to a task with id '%s'", userId, taskId)
      );
    }

    return optional.get();
  }

  @Override
  public List<TaskUser> getAllByTaskId(Long id) {

    return taskUserRepository.getAllByTask_Id(id);
  }

  @Override
  public List<TaskUser> getAllByUserId(Long id) {

    return taskUserRepository.getAllByUser_Id(id);
  }

  @Override
  public TaskUser update(TaskUser taskUser) {

    return taskUserRepository.save(taskUser);
  }

  @Override
  public void delete(Long taskId, Long userId) {
    TaskUser taskUser = get(taskId, userId);
    taskUserRepository.deleteByTask_IdAndUser_Id(taskId, userId);
  }
}
