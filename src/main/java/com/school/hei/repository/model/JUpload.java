package com.school.hei.repository.model;

import jakarta.persistence.*;
import java.time.Instant;
import java.util.UUID;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "upload")
public class JUpload {
  @Id @GeneratedValue private UUID id;

  @Column(nullable = false)
  private String fileName;

  @Column(nullable = false, unique = true)
  private String email;

  @CreationTimestamp private Instant createdAt;
}
