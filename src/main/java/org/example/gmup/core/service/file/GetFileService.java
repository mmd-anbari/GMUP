package org.example.gmup.core.service.file;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.gmup.core.domain.FileMetaData;
import org.example.gmup.core.domain.exception.FIleNotExistsException;
import org.example.gmup.core.domain.exception.FileDownloadAccessDeniedException;
import org.example.gmup.port.inbound.file.PrivateGetFileUC;
import org.example.gmup.port.inbound.file.PublicGetFileUC;
import org.example.gmup.port.outbound.file.GetFileMetaDataPort;
import org.example.gmup.port.outbound.file.GetFilePresidedUrlPort;
import org.example.gmup.port.outbound.file.UpdateFileMetaDataAfterDownloadPort;

import java.util.List;
import java.util.Optional;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetFileService implements PrivateGetFileUC , PublicGetFileUC {


    private GetFileMetaDataPort getFileMetaDataPort;
    private GetFilePresidedUrlPort getFilePresidedUrlPort;
    private UpdateFileMetaDataAfterDownloadPort updateFileMetaDataAfterDownloadPort;


    @Override
    public FileMetaData getFileMetaData(String fileName ,long userId) {

        Optional<FileMetaData> fileMetaData = getFileMetaDataPort.getFileMetaData(fileName, userId);
        if (fileMetaData.isEmpty())
            throw new FIleNotExistsException("file meta data not found by filename : " + fileName + "//from GetFileService/getFileMetaDataWithToken ");
        String token = getFilePresidedUrlPort.getFilePresidedUrl(fileMetaData.get());
        updateFileMetaDataAfterDownloadPort.increaseDownloadCount(fileMetaData.get());
        return fileMetaData.get();
    }


    @Override
    public List<FileMetaData> getAllFileMetaData(long userId){
        Optional<List<FileMetaData>> fileMetaDataList = getFileMetaDataPort.getFileMetaDataList(userId);
        if(fileMetaDataList.isEmpty())
            throw new FIleNotExistsException("there is no file uploaded for user by id : " + userId);
        return fileMetaDataList.get();
    }


    @Override
    public FileMetaData getFileMetaData(String shortCode) {
        Optional<FileMetaData> fileMetaData = getFileMetaDataPort.getFileMetaData(shortCode);
        if(fileMetaData.isEmpty())
            throw new FIleNotExistsException("file meta data not found by shortCode : " + shortCode + "//from GetFileService/getFileMetaDataWithToken ");

        if(!fileMetaData.get().isPublic())
            throw new FileDownloadAccessDeniedException("this file is not public for download with shortCode : " + shortCode + "//from GetFileService/getFileMetaDataWithToken ");
        return fileMetaData.get();

    }

    //TODO i can add redis here for caching the FileMetas with key of short code !
    @Override
    public String getFileToken(String shortCode) {
        Optional<FileMetaData> fileMetaData = getFileMetaDataPort.getFileMetaData(shortCode);
        if(fileMetaData.isEmpty())
            throw new FIleNotExistsException("file meta data not found by shortCode : " + shortCode + "//from GetFileService/getFileMetaDataWithToken ");

        if(!fileMetaData.get().isPublic())
            throw new FileDownloadAccessDeniedException("this file is not public for download with shortCode : " + shortCode + "//from GetFileService/getFileMetaDataWithToken ");
        return getFilePresidedUrlPort.getFilePresidedUrl(fileMetaData.get());

    }


}
