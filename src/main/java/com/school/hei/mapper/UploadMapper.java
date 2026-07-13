package com.school.hei.mapper;

import com.school.hei.model.Upload;
import com.school.hei.repository.model.JUpload;
import org.springframework.stereotype.Component;

@Component
public class UploadMapper {
  public Upload toModel(JUpload entity) {
    return Upload.builder()
        .id(entity.getId())
        .fileName(entity.getFileName())
        .email(entity.getEmail())
        .createdAt(entity.getCreatedAt())
        .build();
  }

  public JUpload toEntity(Upload model) {
    return JUpload.builder()
        .id(model.id())
        .fileName(model.fileName())
        .email(model.email())
        .createdAt(model.createdAt())
        .build();
  }
}
