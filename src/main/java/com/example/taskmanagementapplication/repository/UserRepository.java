package com.example.taskmanagementapplication.repository;

import com.example.taskmanagementapplication.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  Optional<User> findById(Long id);

  Optional<User> findByUsernameIgnoreCase(String username);

  boolean existsById(Long id);

  boolean existsByUsernameIgnoreCase(String username);

}
