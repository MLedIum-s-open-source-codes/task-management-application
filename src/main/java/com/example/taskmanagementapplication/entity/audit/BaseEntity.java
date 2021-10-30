package com.example.taskmanagementapplication.entity.audit;

import com.example.taskmanagementapplication.entity.User;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Embeddable;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.Instant;

@Data
@NoArgsConstructor
@MappedSuperclass
@Embeddable
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity implements Serializable {

  @CreatedBy
  private Long createdBy;

  @CreatedDate
  private Instant createdDate;

  @LastModifiedBy
  private Long lastModifiedBy;

  @LastModifiedDate
  private Instant lastModifiedDate;

}
