package org.example.gmup.adapter.outbound.file;

import org.example.gmup.adapter.outbound.entity.FileMetaDataEntity;
import org.example.gmup.adapter.outbound.jpa.FileRepositoryJpa;
import org.example.gmup.core.domain.enumerated.FileMetaData;
import org.example.gmup.mapper.FileMapper;
import org.example.gmup.port.outbound.file.CheckFileValidationsPort;
import org.example.gmup.port.outbound.file.SaveFileMetaDataPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UploadFileMetaDataAdapter implements CheckFileValidationsPort, SaveFileMetaDataPort {

    private FileRepositoryJpa fileRepositoryJpa;
    private FileMapper fileMapper;

    @Autowired
    public UploadFileMetaDataAdapter(FileRepositoryJpa fileRepositoryJpa, FileMapper fileMapper) {
        this.fileRepositoryJpa = fileRepositoryJpa;
        this.fileMapper = fileMapper;
    }

    @Override
    public boolean isDuplicatedFileName(String fileName) {
        return fileRepositoryJpa.existsFileByName(fileName);
    }

    @Override
    public void saveMetaData(FileMetaData fileMetaData) {
        FileMetaDataEntity fileMetaDataEntity = fileMapper.toEntity(fileMetaData);
        fileRepositoryJpa.save(fileMetaDataEntity);
    }
}
