package org.example.gmup.core.service.file;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.gmup.core.domain.FileDownloadWithToken;
import org.example.gmup.core.domain.FileMetaData;
import org.example.gmup.core.domain.exception.FIleNotExistsException;
import org.example.gmup.port.inbound.file.GetFileUC;
import org.example.gmup.port.outbound.file.GetFileMetaDataPort;
import org.example.gmup.port.outbound.file.GetFilePresidedUrlPort;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetFileService implements GetFileUC {


    private GetFileMetaDataPort getFileMetaDataPort;
    private GetFilePresidedUrlPort getFilePresidedUrlPort;


    @Override
    public FileDownloadWithToken getFileMetaDataWithToken(String fileName , Long userId) {
        FileMetaData fileMetaData = getFileMetaDataPort.getFileMetaData(fileName, userId);
        if (fileMetaData == null)
            throw new FIleNotExistsException("file meta data not found by filename : " + fileName + "//from GetFileMetaDataAdapter");
        String token = getFilePresidedUrlPort.getFilePresidedUrl(fileMetaData);
        return new FileDownloadWithToken(fileMetaData , token);
    }
    @Override
    public List<FileDownloadWithToken> getFilesMetaDataWithToken(Long userId) {
        return List.of();
    }




}
