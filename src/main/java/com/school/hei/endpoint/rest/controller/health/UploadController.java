package com.school.hei.endpoint.rest.controller.health;

import com.school.hei.dto.UploadRequest;
import com.school.hei.model.Upload;
import com.school.hei.service.EmailService;
import com.school.hei.service.UploadService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class UploadController {
  private final UploadService uploadService;
  private final EmailService emailService;

  @PostMapping("/updloads")
  public ResponseEntity<Upload> uploadFile(@ModelAttribute UploadRequest request) {
    try {
      Upload upload = uploadService.uploadFile(request);
      String bucketKey = "uploads/" + upload.id() + "_" + upload.fileName();
      String presignedUrl = uploadService.getPresignedUrl(bucketKey);

      emailService.sendUploadConfirmation(request.email(), upload.fileName(), presignedUrl);

      return ResponseEntity.status(HttpStatus.CREATED).body(upload);

    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }

  @GetMapping
  public ResponseEntity<List<Upload>> getAllUploads() {
    return ResponseEntity.ok(uploadService.getAllUploads());
  }
}
