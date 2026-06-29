package org.example.gmup.port.outbound.file;

import org.example.gmup.core.domain.FileMetaData;

public interface UpdateFileMetaDataAfterDownloadPort {

    void increaseDownloadCount(FileMetaData fileMetaData);

}
