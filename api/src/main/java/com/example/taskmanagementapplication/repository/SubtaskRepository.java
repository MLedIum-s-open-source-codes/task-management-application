package com.example.taskmanagementapplication.repository;

import com.example.taskmanagementapplication.entity.Subtask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubtaskRepository extends JpaRepository<Subtask, Long> {

  @Override
  Optional<Subtask> findById(Long id);

  List<Subtask> findAllByTaskId(Long taskId);

}
