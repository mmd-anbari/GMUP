package org.example.gmup.adapter.inbound;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.example.gmup.core.domain.File;
import org.example.gmup.mapper.FileMapper;
import org.example.gmup.port.inbound.file.UploadFileUC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
    public void uploadFile(@RequestParam("file") MultipartFile fileToUpload) {

        File file = fileMapper.toDomainFromMultipartFile(fileToUpload);
        file.getFileMetaData().setUserId(1L);
        boolean isUploaded = uploadFileUC.uploadFile(file);

        if (isUploaded) {
            System.out.println("Uploaded File");
        } else {
            System.out.println("Not Uploaded");
        }
    }

}
