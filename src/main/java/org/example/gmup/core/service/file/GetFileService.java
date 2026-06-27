package org.example.gmup.core.service.file;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.gmup.core.domain.FileDownloadWithToken;
import org.example.gmup.core.domain.FileMetaData;
import org.example.gmup.core.domain.exception.FIleNotExistsException;
import org.example.gmup.core.domain.exception.FileDownloadAccessDeniedException;
import org.example.gmup.port.inbound.file.GetFileUC;
import org.example.gmup.port.outbound.file.GetFileMetaDataPort;
import org.example.gmup.port.outbound.file.GetFilePresidedUrlPort;
import org.example.gmup.port.outbound.file.UpdateFileMetaDataAfterDownloadPort;

import java.util.List;
import java.util.Optional;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetFileService implements GetFileUC {


    private GetFileMetaDataPort getFileMetaDataPort;
    private GetFilePresidedUrlPort getFilePresidedUrlPort;
    private UpdateFileMetaDataAfterDownloadPort updateFileMetaDataAfterDownloadPort;


    @Override
    public FileDownloadWithToken getFileMetaDataWithToken(String fileName , Long userId) {
        Optional<FileMetaData> fileMetaData = getFileMetaDataPort.getFileMetaData(fileName, userId);
        if (fileMetaData.isEmpty())
            throw new FIleNotExistsException("file meta data not found by filename : " + fileName + "//from GetFileService/getFileMetaDataWithToken ");
        String token = getFilePresidedUrlPort.getFilePresidedUrl(fileMetaData.get());
        updateFileMetaDataAfterDownloadPort.increaseDownloadCount(fileMetaData.get());
        return new FileDownloadWithToken(fileMetaData.get() , token);
    }


    @Override
    public List<FileDownloadWithToken> getFilesMetaDataWithToken(Long userId) {
        return List.of();
    }


    @Override
    public FileDownloadWithToken getFileMetaDataWithToken(String shortCode) {
        Optional<FileMetaData> fileMetaData = getFileMetaDataPort.getFileMetaData(shortCode);
        if(fileMetaData.isEmpty())
            throw new FIleNotExistsException("file meta data not found by shortCode : " + shortCode + "//from GetFileService/getFileMetaDataWithToken ");

        if(!fileMetaData.get().isPublic())
            throw new FileDownloadAccessDeniedException("this file is not public for download with shortCode : " + shortCode + "//from GetFileService/getFileMetaDataWithToken ");
        String token = getFilePresidedUrlPort.getFilePresidedUrl(fileMetaData.get());
        return new FileDownloadWithToken(fileMetaData.get() , token);

    }


}
