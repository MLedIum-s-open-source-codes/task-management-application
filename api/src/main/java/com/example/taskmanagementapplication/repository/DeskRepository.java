package com.example.taskmanagementapplication.repository;

import com.example.taskmanagementapplication.entity.Desk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DeskRepository extends JpaRepository<Desk, Long> {

  @Override
  Optional<Desk> findById(Long id);

  List<Desk> findAllByUserId(Long userId);

}
