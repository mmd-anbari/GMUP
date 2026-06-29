package org.example.gmup.adapter.inbound;
import org.example.gmup.adapter.inbound.dto.file.FileMenu;
import org.example.gmup.core.domain.FileDownloadWithToken;
import org.example.gmup.core.dto.FileUploadCommand;
import org.example.gmup.mapper.FileMapper;
import org.example.gmup.port.inbound.file.GetFileUC;
import org.example.gmup.port.inbound.file.UploadFileUC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

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


    @PostMapping(value = "/files", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void uploadFile(@RequestParam("file") MultipartFile fileToUpload ,
                           @RequestParam boolean isPublic,
                           @RequestParam String fileName) throws IOException {

        FileUploadCommand fileUploadCommand = new FileUploadCommand(
                fileToUpload.getOriginalFilename(),
                fileName,
                fileToUpload.getContentType(),
                fileToUpload.getInputStream(),
                isPublic
        );

        UserDetails userDetails =(UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();
        boolean b = uploadFileUC.uploadFile(fileUploadCommand, username);
        if (b)
            System.out.println("Upload file successful");
        else {
            System.out.println("Upload file failed");
        }

    }

    //special for the authenticated user !
    @GetMapping("/files/infos")
    public ResponseEntity<FileMenu> getFileMetaData(@RequestParam("fileName") String fileName) {

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = userDetails.getUsername();

            FileDownloadWithToken fileDownloadWithToken = getFileUC.getFileMetaDataWithToken(fileName ,username);
            FileMenu fileMenu = fileMapper.fromFileWithDownloadTokenToFileMenu(fileDownloadWithToken);
            return new ResponseEntity<>(fileMenu, HttpStatus.OK);


    }

    @GetMapping("/files")
    public ResponseEntity<FileMenu> getFileMetaDataByShortCode(@RequestParam("shortCode") String shortCode) {


            FileDownloadWithToken fileDownloadWithToken = getFileUC.getFileMetaDataWithToken(shortCode);
            FileMenu fileMenu = fileMapper.fromFileWithDownloadTokenToFileMenu(fileDownloadWithToken);
            return new ResponseEntity<FileMenu>(fileMenu, HttpStatus.OK);

    }


}
