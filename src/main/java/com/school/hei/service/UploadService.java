package com.school.hei.service;

import com.school.hei.dto.UploadRequest;
import com.school.hei.file.bucket.BucketComponent;
import com.school.hei.mapper.UploadMapper;
import com.school.hei.model.Upload;
import com.school.hei.repository.UploadRepository;
import com.school.hei.repository.model.JUpload;
import java.io.File;
import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@AllArgsConstructor
public class UploadService {
  private final UploadRepository repository;
  private final UploadMapper mapper;
  private final BucketComponent bucketComponent;

  @SneakyThrows
  public Upload uploadFile(UploadRequest request) {
    MultipartFile file = request.file();
    String originalFilename = file.getOriginalFilename();
    String bucketKey = "uploads/" + UUID.randomUUID() + "_" + originalFilename;

    File tempFile = File.createTempFile("upload-", originalFilename);
    file.transferTo(tempFile);

    bucketComponent.upload(tempFile, bucketKey);

    tempFile.delete();

    JUpload jUpload =
        JUpload.builder()
            .id(UUID.randomUUID())
            .fileName(originalFilename)
            .email(request.email())
            .createdAt(Instant.now())
            .build();

    return mapper.toModel(repository.save(jUpload));
  }

  public List<Upload> getAllUploads() {
    return repository.findAll().stream().map(mapper::toModel).collect(Collectors.toList());
  }
}
