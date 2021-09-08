package com.example.taskmanagementapplication.repository;

import com.example.taskmanagementapplication.entity.TaskUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface TaskUserRepository extends JpaRepository<TaskUser, Long> {

  Optional<TaskUser> findByTask_IdAndUser_Id(Long taskId, Long userId);

  List<TaskUser> getAllByUser_Id(Long userId);

  List<TaskUser> getAllByTask_Id(Long taskId);

  boolean existsByTask_IdAndUser_Id(Long taskId, Long userId);

  @Modifying
  @Transactional
  @Query(value = "DELETE FROM tasks_users WHERE task_id = :taskId AND user_id = :userId", nativeQuery = true)
  void deleteByTask_IdAndUser_Id(@Param("taskId") Long taskId, @Param("userId") Long userId);

}
