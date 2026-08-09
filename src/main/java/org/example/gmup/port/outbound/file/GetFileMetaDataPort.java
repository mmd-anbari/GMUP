package org.example.gmup.port.outbound.file;

import org.example.gmup.core.domain.File;
import org.example.gmup.core.domain.FileMetaData;

import java.util.List;
import java.util.Optional;

public interface GetFileMetaDataPort {

    Optional<FileMetaData> getFileMetaData(String filename, Long userId);

    Optional<FileMetaData> getFileMetaData(String shortCode);

    List<FileMetaData> getFileMetaDataList(Long userId);

}
