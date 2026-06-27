package org.example.gmup.port.outbound.file;

import org.example.gmup.core.domain.File;
import org.example.gmup.core.domain.FileMetaData;

import java.util.List;

public interface GetFileMetaDataPort {

    FileMetaData getFileMetaData(String filename , Long userId);

    FileMetaData getFileMetaData(String shortCode);

}
