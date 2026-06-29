package org.example.gmup.adapter.outbound.file;

import org.example.gmup.adapter.outbound.entity.FileMetaDataEntity;
import org.example.gmup.adapter.outbound.entity.UserEntity;
import org.example.gmup.adapter.outbound.jpa.FileMetaDataRepositoryJpa;
import org.example.gmup.adapter.outbound.jpa.UserRepositoryJpa;
import org.example.gmup.core.domain.FileMetaData;
import org.example.gmup.mapper.FileMapper;
import org.example.gmup.port.outbound.file.CheckFileValidationsPort;
import org.example.gmup.port.outbound.file.SaveFileMetaDataPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UploadFileMetaDataAdapter implements CheckFileValidationsPort, SaveFileMetaDataPort {

    private UserRepositoryJpa userRepositoryJpa;
    private FileMetaDataRepositoryJpa fileMetaDataRepositoryJpa;
    private FileMapper fileMapper;

    @Autowired
    public UploadFileMetaDataAdapter(FileMetaDataRepositoryJpa fileMetaDataRepositoryJpa, UserRepositoryJpa userRepositoryJpa, FileMapper fileMapper) {
        this.fileMetaDataRepositoryJpa = fileMetaDataRepositoryJpa;
        this.userRepositoryJpa = userRepositoryJpa;
        this.fileMapper = fileMapper;
    }

    @Override
    public boolean isDuplicatedFileName(String fileName) {
        return fileMetaDataRepositoryJpa.existsByOriginalFilename(fileName);
    }

    @Override
    public void saveMetaData(FileMetaData fileMetaData , Long userId) {
        UserEntity referenceById = userRepositoryJpa.getReferenceById(userId);
        FileMetaDataEntity fileMetaDataEntity = fileMapper.toEntity(fileMetaData);
        fileMetaDataEntity.setUser(referenceById);

        fileMetaDataRepositoryJpa.save(fileMetaDataEntity);
    }
}
