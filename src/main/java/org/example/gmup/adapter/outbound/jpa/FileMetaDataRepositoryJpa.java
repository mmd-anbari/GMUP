package org.example.gmup.adapter.outbound.jpa;

import org.example.gmup.adapter.outbound.entity.FileMetaDataEntity;
import org.example.gmup.core.domain.FileMetaData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface FileMetaDataRepositoryJpa extends JpaRepository<FileMetaDataEntity, Long>{
    boolean existsByOriginalFilename(String originalFilename);


    Optional<FileMetaDataEntity> findFileMetaDataEntityByOriginalFilenameAndUser_Id(String originalFilename, Long userId);

    Optional<FileMetaDataEntity> findFileMetaDataEntityByFileNameAndUser_Id(String fileName, Long userId);

    @Modifying
    @Transactional
    @Query("UPDATE FileMetaDataEntity f SET f.downloadCount = f.downloadCount + 1 WHERE f.id = :id")
    int incrementDownloadCount(@Param("id") Long id);

    Optional<FileMetaDataEntity> getFileMetaDataEntityByShortCode(String shortCode);

    Optional<List<FileMetaData>> findByUser_Id(Long userId);
}
