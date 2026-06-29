package org.example.gmup.port.outbound.file;

import org.example.gmup.core.domain.FileMetaData;

import java.io.InputStream;

public interface GetFilePresidedUrlPort {

    String getFilePresidedUrl(FileMetaData fileMetaData);

}
