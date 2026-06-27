package org.example.gmup.adapter.outbound.file;
import org.example.gmup.adapter.outbound.entity.FileMetaDataEntity;
import org.example.gmup.adapter.outbound.jpa.FileMetaDataRepositoryJpa;
import org.example.gmup.core.domain.FileMetaData;
import org.example.gmup.core.domain.exception.FIleNotExistsException;
import org.example.gmup.mapper.FileMapper;
import org.example.gmup.port.outbound.file.GetFileMetaDataPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class GetFileMetaDataAdapter implements GetFileMetaDataPort {

    private FileMetaDataRepositoryJpa fileMetaDataRepositoryJpa;
    private FileMapper fileMapper;


    @Autowired
    public GetFileMetaDataAdapter(FileMetaDataRepositoryJpa fileMetaDataRepositoryJpa , FileMapper fileMapper) {
        this.fileMetaDataRepositoryJpa = fileMetaDataRepositoryJpa;
        this.fileMapper = fileMapper;
    }

    @Override
    public FileMetaData getFileMetaData(String filename , Long userId) {
        Optional<FileMetaDataEntity> fileMetaDataEntity =
                fileMetaDataRepositoryJpa.findFileMetaDataEntityByOriginalFilenameAndUser_Id(filename,userId);
        if(fileMetaDataEntity.isEmpty())
            return null;

        FileMetaDataEntity realFileMetaDataEntity = fileMetaDataEntity.get();

        return fileMapper.toDomain(realFileMetaDataEntity);

    }

    @Override
    public FileMetaData getFileMetaData(String shortCode) {
        Optional<FileMetaDataEntity> fileMetaDataEntity = fileMetaDataRepositoryJpa.getFileMetaDataEntityByShortCode(shortCode);
        if(fileMetaDataEntity.isEmpty())
            return null;
        FileMetaDataEntity realFileMetaDataEntity = fileMetaDataEntity.get();
        return fileMapper.toDomain(realFileMetaDataEntity);
    }
}
