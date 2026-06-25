package org.example.gmup.adapter.inbound;

import org.example.gmup.core.domain.File;
import org.example.gmup.core.domain.User;
import org.example.gmup.core.domain.FileMetaData;
import org.example.gmup.core.dto.FileUploadCommand;
import org.example.gmup.mapper.FileMapper;
import org.example.gmup.port.inbound.file.UploadFileUC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController("/fileManager")
public class FileManagementAdapter {

    private UploadFileUC uploadFileUC;
    private FileMapper fileMapper;

    @Autowired
    public FileManagementAdapter(UploadFileUC uploadFileUC, FileMapper fileMapper) {
        this.uploadFileUC = uploadFileUC;
        this.fileMapper = fileMapper;
    }

    //TODO amortize File object into fileMetaData and stream when i use mapping .
    @PostMapping("/file")
    public void uploadFile(@RequestParam("file") MultipartFile fileToUpload) throws IOException {

        FileUploadCommand fileUploadCommand= new FileUploadCommand(
                fileToUpload.getOriginalFilename(),
                fileToUpload.getContentType(),
                fileToUpload.getInputStream()
        );

        Long userId = 1L ;
        boolean b = uploadFileUC.uploadFile(fileUploadCommand , userId);
        if (b)
            System.out.println("Upload file successful");
        else {
            System.out.println("Upload file failed");
        }

    }
}
