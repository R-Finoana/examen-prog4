package com.school.hei.repository;

import com.school.hei.repository.model.JUpload;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UploadRepository extends JpaRepository<JUpload, UUID> {}
