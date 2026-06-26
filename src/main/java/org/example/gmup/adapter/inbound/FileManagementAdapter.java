package org.example.gmup.adapter.inbound;

import org.example.gmup.adapter.inbound.dto.file.FileMenu;
import org.example.gmup.adapter.inbound.exception.StorageProblemException;
import org.example.gmup.core.domain.File;
import org.example.gmup.core.domain.FileDownloadWithToken;
import org.example.gmup.core.domain.User;
import org.example.gmup.core.domain.FileMetaData;
import org.example.gmup.core.domain.exception.FIleNotExistsException;
import org.example.gmup.core.dto.FileMenuCommand;
import org.example.gmup.core.dto.FileUploadCommand;
import org.example.gmup.mapper.FileMapper;
import org.example.gmup.port.inbound.file.GetFileUC;
import org.example.gmup.port.inbound.file.UploadFileUC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/fileManager")
public class FileManagementAdapter {

    private UploadFileUC uploadFileUC;
    private GetFileUC getFileUC;
    private FileMapper fileMapper;

    @Autowired
    public FileManagementAdapter(UploadFileUC uploadFileUC, GetFileUC getFileUC, FileMapper fileMapper) {
        this.uploadFileUC = uploadFileUC;
        this.getFileUC = getFileUC;
        this.fileMapper = fileMapper;
    }

    //TODO amortize File object into fileMetaData and stream when i use mapping .
    @PostMapping(value = "/file", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void uploadFile(@RequestParam("file") MultipartFile fileToUpload) throws IOException {

        FileUploadCommand fileUploadCommand = new FileUploadCommand(
                fileToUpload.getOriginalFilename(),
                fileToUpload.getContentType(),
                fileToUpload.getInputStream()
        );

        Long userId = 1L;
        boolean b = uploadFileUC.uploadFile(fileUploadCommand, userId);
        if (b)
            System.out.println("Upload file successful");
        else {
            System.out.println("Upload file failed");
        }

    }

    @GetMapping("/file/{fileName}")
    public FileMenu getFileMetaData(@RequestParam String fileName) {
        Long userId = 1L;
        try {
            FileDownloadWithToken fileDownloadWithToken = getFileUC.getFileMetaDataWithToken(fileName , userId);
            FileMenu fileMenu = new FileMenu(fileDownloadWithToken.getFileMetadata().getOriginalFilename(),
                    fileDownloadWithToken.getFileMetadata().getContentType() , fileDownloadWithToken.getToken());
            return fileMenu;
        } catch (FIleNotExistsException e) {
            throw new StorageProblemException(e.getMessage());
        }

    }


}
