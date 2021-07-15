package com.example.taskmanagementapplication.repository;

import com.example.taskmanagementapplication.entity.Desk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DeskRepository extends JpaRepository<Desk, Long> {

  @Override
  Optional<Desk> findById(Long id);

  @Query("select d from Desk d join User u where u.id = :id")
  List<Desk> findAllByUserId(@Param("id") Long userId);

}
