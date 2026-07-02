package org.example.gmup.adapter.inbound;

import lombok.RequiredArgsConstructor;
import org.example.gmup.adapter.inbound.dto.file.ProfileFileMetaData;
import org.example.gmup.core.domain.FileMetaData;
import org.example.gmup.mapper.FileMapper;
import org.example.gmup.port.inbound.file.PublicGetFileUC;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
@RequiredArgsConstructor
public class PublicFileManagementAdapter {

    private final PublicGetFileUC publicGetFileUC;
    private final FileMapper fileMapper;






    @GetMapping("/files")
    public ResponseEntity<ProfileFileMetaData> getFileMetaDataByShortCode(@RequestParam("shortCode") String shortCode) {


        FileMetaData fileMetaData = publicGetFileUC.getFileMetaData(shortCode);
        ProfileFileMetaData profileFileMetaData= fileMapper.fromFileMetaToProfileFileMetaData(fileMetaData);
        return new ResponseEntity<>(profileFileMetaData, HttpStatus.OK);

    }

    @GetMapping("/files/token")
    public String getToken(@RequestParam String shortCode) {
        return publicGetFileUC.getFileToken(shortCode);
    }



}
