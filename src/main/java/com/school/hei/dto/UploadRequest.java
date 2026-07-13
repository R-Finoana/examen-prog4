package com.school.hei.dto;

import lombok.Builder;
import org.springframework.web.multipart.MultipartFile;

@Builder
public record UploadRequest(MultipartFile file, String email) {}
