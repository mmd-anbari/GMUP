package org.example.gmup.adapter.outbound.file;

import org.example.gmup.adapter.outbound.jpa.FileMetaDataRepositoryJpa;
import org.example.gmup.core.domain.FileMetaData;
import org.example.gmup.port.outbound.file.UpdateFileMetaDataAfterDownloadPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UpdateFileMetaDataAdapter implements UpdateFileMetaDataAfterDownloadPort {

    private FileMetaDataRepositoryJpa fileMetaDataRepositoryJpa;

    @Autowired
    public UpdateFileMetaDataAdapter(FileMetaDataRepositoryJpa fileMetaDataRepositoryJpa) {
        this.fileMetaDataRepositoryJpa = fileMetaDataRepositoryJpa;
    }

    @Override
    public void increaseDownloadCount(FileMetaData fileMetaData) {

        fileMetaDataRepositoryJpa.incrementDownloadCount(fileMetaData.getId());

    }
}
