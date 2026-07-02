package org.example.gmup.adapter.inbound;


import lombok.RequiredArgsConstructor;
import org.example.gmup.adapter.inbound.dto.file.ProfileFileMetaData;
import org.example.gmup.adapter.inbound.dto.user.UserProfileMenu;
import org.example.gmup.adapter.outbound.security.UserSecurity;
import org.example.gmup.core.domain.FileMetaData;
import org.example.gmup.core.dto.FileUploadCommand;
import org.example.gmup.mapper.FileMapper;
import org.example.gmup.mapper.UserMapper;
import org.example.gmup.port.inbound.file.PrivateGetFileUC;
import org.example.gmup.port.inbound.file.UploadFileUC;
import org.example.gmup.port.inbound.user.GetUserProfileUC;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/dashboard")
@RequiredArgsConstructor
public class DashboardManagementAdapter {

    private final PrivateGetFileUC privateGetFileUC;
    private final GetUserProfileUC getUserProfileUC;
    private final UploadFileUC uploadFileUC;

    private final UserMapper userMapper;
    private final FileMapper fileMapper;


    @GetMapping("/profiles")
    public ResponseEntity<UserProfileMenu> getProfile() {

        UserSecurity user = (UserSecurity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return new ResponseEntity<>(userMapper.fromUserToUserProfileMenu(getUserProfileUC.getUserProfile(user.getId())), HttpStatus.OK);
    }

    @GetMapping("/files")
    public List<ProfileFileMetaData> getFiles() {

        UserSecurity user = (UserSecurity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return privateGetFileUC.getAllFileMetaData(user.getId()).stream().
                map(fileMapper::fromFileMetaToProfileFileMetaData).toList();


    }

    @PostMapping(value = "/files", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void uploadFile(@RequestParam("file") MultipartFile fileToUpload,
                           @RequestParam boolean isPublic,
                           @RequestParam String fileName) throws IOException {

        FileUploadCommand fileUploadCommand = new FileUploadCommand(
                fileToUpload.getOriginalFilename(),
                fileName,
                fileToUpload.getContentType(),
                fileToUpload.getInputStream(),
                isPublic
        );

        UserSecurity userSecurity = (UserSecurity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        long userId = userSecurity.getId();
        boolean b = uploadFileUC.uploadFile(fileUploadCommand, userId);
        if (b)
            System.out.println("Upload file successful");
        else {
            System.out.println("Upload file failed");
        }

    }

    //special for the authenticated user !
    @GetMapping("/files/infos")
    public ResponseEntity<ProfileFileMetaData> getFileMetaData(@RequestParam("fileName") String fileName) {

        UserSecurity userSecurity = (UserSecurity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        long userId = userSecurity.getId();

        FileMetaData fileMetaData = privateGetFileUC.getFileMetaData(fileName, userId);
        ProfileFileMetaData profileFileMetaData = fileMapper.fromFileMetaToProfileFileMetaData(fileMetaData);
        return new ResponseEntity<>(profileFileMetaData, HttpStatus.OK);


    }

    @GetMapping("/files/token")
    public String getToken(@RequestParam String shortCode) {
        return privateGetFileUC.getFileToken(shortCode);
    }

}
