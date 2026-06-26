package org.example.gmup.adapter.outbound.jpa;

import org.example.gmup.adapter.outbound.entity.FileMetaDataEntity;
import org.example.gmup.core.domain.FileMetaData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FileMetaDataRepositoryJpa extends JpaRepository<FileMetaDataEntity, Long>{
    boolean existsByOriginalFilename(String originalFilename);


    Optional<FileMetaDataEntity> findFileMetaDataEntityByOriginalFilenameAndUser_Id(String originalFilename, Long userId);
}
