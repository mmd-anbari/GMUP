package org.example.gmup.port.inbound.file;
import org.example.gmup.core.domain.FileDownloadWithToken;
import java.util.List;

public interface GetFileUC {

    FileDownloadWithToken getFileMetaDataWithToken(String fileName,String username);

    List<FileDownloadWithToken> getFilesMetaDataWithToken(Long userId);

    FileDownloadWithToken getFileMetaDataWithToken(String shortCode);




}
