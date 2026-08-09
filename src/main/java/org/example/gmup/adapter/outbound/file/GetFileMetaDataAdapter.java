package org.example.gmup.adapter.outbound.file;

import lombok.val;
import org.example.gmup.adapter.outbound.entity.FileMetaDataEntity;
import org.example.gmup.adapter.outbound.jpa.FileMetaDataRepositoryJpa;
import org.example.gmup.core.domain.FileMetaData;
import org.example.gmup.core.domain.exception.FIleNotExistsException;
import org.example.gmup.mapper.FileMapper;
import org.example.gmup.port.outbound.file.GetFileMetaDataPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class GetFileMetaDataAdapter implements GetFileMetaDataPort {

    private FileMetaDataRepositoryJpa fileMetaDataRepositoryJpa;
    private FileMapper fileMapper;


    @Autowired
    public GetFileMetaDataAdapter(FileMetaDataRepositoryJpa fileMetaDataRepositoryJpa, FileMapper fileMapper) {
        this.fileMetaDataRepositoryJpa = fileMetaDataRepositoryJpa;
        this.fileMapper = fileMapper;
    }

    @Override
    public Optional<FileMetaData> getFileMetaData(String filename, Long userId) {
        Optional<FileMetaDataEntity> fileMetaDataEntity =
                fileMetaDataRepositoryJpa.findFileMetaDataEntityByFileNameAndUser_Id(filename, userId);

        return fileMetaDataEntity.map(fileMapper::toDomain);

    }

    @Override
    public Optional<FileMetaData> getFileMetaData(String shortCode) {
        Optional<FileMetaDataEntity> fileMetaDataEntity = fileMetaDataRepositoryJpa.getFileMetaDataEntityByShortCode(shortCode);
        return fileMetaDataEntity.map(fileMapper::toDomain);
    }

    @Override
    public List<FileMetaData> getFileMetaDataList(Long userId) {
        List<FileMetaDataEntity> metaDataEntities = fileMetaDataRepositoryJpa.findByUser_Id(userId);
        return metaDataEntities.stream().map(fileMapper::toDomain).toList();
    }
}
