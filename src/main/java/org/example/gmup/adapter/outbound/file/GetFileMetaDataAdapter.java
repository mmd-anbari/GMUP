package org.example.gmup.adapter.outbound.file;
import org.example.gmup.adapter.outbound.entity.FileMetaDataEntity;
import org.example.gmup.adapter.outbound.jpa.FileMetaDataRepositoryJpa;
import org.example.gmup.core.domain.FileMetaData;
import org.example.gmup.core.domain.exception.FIleNotExistsException;
import org.example.gmup.port.outbound.file.GetFileMetaDataPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class GetFileMetaDataAdapter implements GetFileMetaDataPort {

    private FileMetaDataRepositoryJpa fileMetaDataRepositoryJpa;


    @Autowired
    public GetFileMetaDataAdapter(FileMetaDataRepositoryJpa fileMetaDataRepositoryJpa) {
        this.fileMetaDataRepositoryJpa = fileMetaDataRepositoryJpa;
    }

    @Override
    public FileMetaData getFileMetaData(String filename , Long userId) {
        Optional<FileMetaDataEntity> fileMetaDataEntity =
                fileMetaDataRepositoryJpa.findFileMetaDataEntityByOriginalFilenameAndUser_Id(filename,userId);
        if(fileMetaDataEntity.isEmpty())
            return null;

        FileMetaData fileMetaData = new FileMetaData();
        fileMetaData.setId(fileMetaDataEntity.get().getId());
        fileMetaData.setOriginalFilename(fileMetaDataEntity.get().getOriginalFilename());
        fileMetaData.setContentType(fileMetaDataEntity.get().getContentType());
        fileMetaData.setPath(fileMetaDataEntity.get().getPath());
        return fileMetaData;

    }
}
