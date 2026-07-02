package org.example.gmup.port.inbound.file;

import org.example.gmup.core.domain.FileMetaData;

public interface PublicGetFileUC {

    FileMetaData getFileMetaData(String shortCode);

    String getFileToken(String shortCode);
}
