package org.example.gmup.adapter.outbound.jpa;

import org.example.gmup.adapter.outbound.entity.FileMetaDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepositoryJpa extends JpaRepository<FileMetaDataEntity, Long>{
    boolean existsByOriginalFilename(String originalFilename);
}
