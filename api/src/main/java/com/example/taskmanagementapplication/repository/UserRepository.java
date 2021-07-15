package com.example.taskmanagementapplication.repository;

import com.example.taskmanagementapplication.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  @Override
  Optional<User> findById(Long id);

}
