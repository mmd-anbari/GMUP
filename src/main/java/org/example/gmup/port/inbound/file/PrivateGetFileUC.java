package org.example.gmup.port.inbound.file;
import org.example.gmup.core.domain.FileMetaData;

import java.util.List;

public interface PrivateGetFileUC {

    FileMetaData getFileMetaData(String fileName,long userId);

    List<FileMetaData> getAllFileMetaData(long userId);


    String getFileToken(String shortCode);
}
