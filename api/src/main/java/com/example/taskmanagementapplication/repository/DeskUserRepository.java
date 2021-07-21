package com.example.taskmanagementapplication.repository;

import com.example.taskmanagementapplication.entity.DeskUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DeskUserRepository extends JpaRepository<DeskUser, Long> {

    Optional<DeskUser> findByDesk_IdAndUser_Id(Long deskId, Long userId);

    List<DeskUser> getAllByUser_Id(Long userId);

    List<DeskUser> getAllByDesk_Id(Long deskId);

    void deleteByDesk_IdAndUser_Id(Long deskId, Long userId);

}
