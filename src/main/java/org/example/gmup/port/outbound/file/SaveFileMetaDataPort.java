package org.example.gmup.port.outbound.file;
import org.example.gmup.core.domain.enumerated.FileMetaData;

public interface SaveFileMetaDataPort {
    void saveMetaData(FileMetaData fileMetaData);
}
