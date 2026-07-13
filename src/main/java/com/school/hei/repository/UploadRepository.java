package com.school.hei.repository;

import com.school.hei.repository.model.JUpload;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UploadRepository extends JpaRepository<JUpload, UUID> {}
