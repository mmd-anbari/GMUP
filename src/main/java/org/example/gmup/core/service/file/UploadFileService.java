package org.example.gmup.core.service.file;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.example.gmup.core.domain.File;
import org.example.gmup.core.domain.FileMetaData;
import org.example.gmup.core.domain.User;
import org.example.gmup.core.dto.FileUploadCommand;
import org.example.gmup.port.inbound.file.UploadFileUC;
import org.example.gmup.port.outbound.file.CheckFileValidationsPort;
import org.example.gmup.port.outbound.file.SaveFileMetaDataPort;
import org.example.gmup.port.outbound.file.SaveFileStreamPort;
import org.example.gmup.port.outbound.user.UserInformationPort;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
public class UploadFileService implements UploadFileUC {

    private CheckFileValidationsPort checkFileValidationsPort;
    private SaveFileStreamPort saveFileStreamPort;
    private SaveFileMetaDataPort saveFileMetaDataPort;
    private UserInformationPort userInformationPort;


    @Override
    public boolean uploadFile(FileUploadCommand fileUploadCommand , String username) {

        User user = userInformationPort.getUserInformation(username).orElseThrow(
                ()-> new UsernameNotFoundException("user by username " + username + " not found! // from UploadFileService/uploadFile")
        );

        Long userId = user.getId();

        if(checkFileValidationsPort.isDuplicatedFileName(fileUploadCommand.originalFilename())){
            return false;
        }

        String pathName = saveFileStreamPort.saveFileStream(
                fileUploadCommand.originalFilename(),
                userId,
                fileUploadCommand.inputStream());

        FileMetaData fileMetaData = extractFileMetaData(fileUploadCommand);
        fileMetaData.setPath(pathName);
        fileMetaData.setCreatedAt(LocalDateTime.now());


        saveFileMetaDataPort.saveMetaData(fileMetaData , userId);



        return true;
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
