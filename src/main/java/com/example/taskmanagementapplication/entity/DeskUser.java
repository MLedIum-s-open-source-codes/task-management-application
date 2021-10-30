package com.example.taskmanagementapplication.entity;

import com.example.taskmanagementapplication.entity.audit.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "desks_users")
public class DeskUser extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id",
            referencedColumnName = "id")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "desk_id",
            referencedColumnName = "id")
    private Desk desk;

    private Boolean isOwner;

    @Builder.Default
    @OneToMany(mappedBy = "deskUser",
        fetch = FetchType.LAZY,
        orphanRemoval = true)
    @EqualsAndHashCode.Exclude
    private Set<TaskUser> tasksUsers = new HashSet<>();

    @Builder.Default
    private Boolean isActive = true;

}
