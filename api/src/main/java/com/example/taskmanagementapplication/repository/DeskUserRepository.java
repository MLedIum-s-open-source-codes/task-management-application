package com.example.taskmanagementapplication.repository;

import com.example.taskmanagementapplication.entity.DeskUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.beans.Transient;
import java.util.List;
import java.util.Optional;

public interface DeskUserRepository extends JpaRepository<DeskUser, Long> {

    Optional<DeskUser> findByDesk_IdAndUser_Id(Long deskId, Long userId);

    List<DeskUser> getAllByUser_Id(Long userId);

    List<DeskUser> getAllByDesk_Id(Long deskId);

    boolean existsByDesk_IdAndUser_Id(Long deskId, Long userId);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM desks_users WHERE desk_id = :deskId AND user_id = :userId", nativeQuery = true)
    void deleteByDesk_IdAndUser_Id(@Param("deskId") Long deskId, @Param("userId") Long userId);

}
