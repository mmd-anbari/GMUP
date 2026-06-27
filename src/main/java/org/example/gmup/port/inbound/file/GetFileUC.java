package org.example.gmup.port.inbound.file;

import org.example.gmup.core.domain.File;
import org.example.gmup.core.domain.FileDownloadWithToken;
import org.example.gmup.core.domain.FileMetaData;
import org.example.gmup.core.dto.FileMenuCommand;

import java.io.InputStream;
import java.util.List;

public interface GetFileUC {

    FileDownloadWithToken getFileMetaDataWithToken(String fileName, Long userId);

    List<FileDownloadWithToken> getFilesMetaDataWithToken(Long userId);

    FileDownloadWithToken getFileMetaDataWithToken(String shortCode);




}
