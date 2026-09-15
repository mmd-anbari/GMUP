package org.example.gmup.core.service.file;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.example.gmup.core.domain.exception.DuplicatedFileNameException;
import org.example.gmup.core.domain.FileMetaData;
import org.example.gmup.core.domain.exception.NotEnoughUploadSpaceException;
import org.example.gmup.core.dto.FileUploadCommand;
import org.example.gmup.port.inbound.file.UploadFileUC;
import org.example.gmup.port.outbound.file.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
public class UploadFileService implements UploadFileUC {

    private CheckFileValidationsPort checkFileValidationsPort;
    private GetFilePresignedUploadUrlPort getFilePresignedUploadUrlPort;
    private SaveFileMetaDataPort saveFileMetaDataPort;
    private UserStorageLimitPort userStorageLimitPort;


    @Override
    public String uploadFile(FileUploadCommand fileUploadCommand , long userId) {

        if(checkFileValidationsPort.isDuplicatedFileName(fileUploadCommand.originalFilename())){
            throw new DuplicatedFileNameException("Duplicated file name with name :" + fileUploadCommand.originalFilename());
        }

        long storageLimit = userStorageLimitPort.getStorageLimit(userId);
        if(storageLimit <= fileUploadCommand.size()){
            throw new NotEnoughUploadSpaceException("you do not have enough space to upload this file by size :" + fileUploadCommand.size());
        }

        long newStorageLimit = storageLimit-fileUploadCommand.size();

        String pathName = userId+"/"+fileUploadCommand.originalFilename();


        FileMetaData fileMetaData = extractFileMetaData(fileUploadCommand);
        fileMetaData.setPath(pathName);
        fileMetaData.setCreatedAt(LocalDateTime.now());

        String upleadUrl = getFilePresignedUploadUrlPort.getFileUpUrl(fileMetaData);

        saveFileMetaDataPort.saveMetaData(fileMetaData , userId);
        userStorageLimitPort.updateStorageLimit(userId, newStorageLimit);


        return upleadUrl;
    }



    private static FileMetaData extractFileMetaData(FileUploadCommand fileUploadCommand) {
        FileMetaData fileMetaData = new FileMetaData();
        fileMetaData.setOriginalFilename(fileUploadCommand.originalFilename());
        fileMetaData.setContentType(fileUploadCommand.contentType());
        fileMetaData.setFileName(fileUploadCommand.fileName());
        fileMetaData.setPublic(fileUploadCommand.isPublic());
        fileMetaData.setShortCode(ShortCodeGenerator.getShortCode());
        return fileMetaData;
    }

}
