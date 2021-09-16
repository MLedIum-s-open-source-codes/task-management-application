package com.example.taskmanagementapplication.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.Auditable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.Optional;

@Data
@NoArgsConstructor
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class CustomAuditable<U, PK extends Serializable> implements Auditable<U, PK, Instant> {

  private static final long serialVersionUID = 141481953116476081L;

  @JsonIgnore
  @ManyToOne
  @JoinColumn(name = "created_by_id",
      columnDefinition = "bigint",
      referencedColumnName = "id")
  @CreatedBy
  private U createdBy;

  @JsonIgnore
  @CreatedDate
  private Instant createdDate;

  @JsonIgnore
  @ManyToOne
  @JoinColumn(name = "last_modified_by_id",
      columnDefinition = "bigint",
      referencedColumnName = "id")
  @LastModifiedBy
  private U lastModifiedBy;

  @JsonIgnore
  @LastModifiedDate
  private Instant lastModifiedDate;

  @Override
  public Optional<U> getCreatedBy() {
    return Optional.ofNullable(this.createdBy);
  }

  @Override
  public Optional<Instant> getCreatedDate() {
    return null == this.createdDate ? Optional.empty() : Optional.of(this.createdDate);
  }

  @Override
  public Optional<U> getLastModifiedBy() {
    return Optional.ofNullable(this.lastModifiedBy);
  }

  @Override
  public Optional<Instant> getLastModifiedDate() {
    return null == this.lastModifiedDate ? Optional.empty() : Optional.of(this.lastModifiedDate);
  }

  @Override
  @Transient
  public boolean isNew() {
    return null == this.getId();
  }

  @Override
  public String toString() {
    return String.format("Entity of type %s with id: %s", this.getClass().getName(), this.getId());
  }

}
