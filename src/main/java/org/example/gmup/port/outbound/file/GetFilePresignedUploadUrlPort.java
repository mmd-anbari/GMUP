package org.example.gmup.port.outbound.file;

import org.example.gmup.core.domain.FileMetaData;

import java.io.InputStream;

public interface GetFilePresignedUploadUrlPort {
    String getFileUpUrl(FileMetaData fileMetaData);
}
