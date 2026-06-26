package org.example.gmup.configuration;

import org.example.gmup.core.service.file.GetFileService;
import org.example.gmup.core.service.file.UploadFileService;
import org.example.gmup.port.inbound.file.GetFileUC;
import org.example.gmup.port.inbound.file.UploadFileUC;
import org.example.gmup.port.outbound.file.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InboundPortConfig {


    @Bean
    public UploadFileUC uploadFile(CheckFileValidationsPort checkFileValidationsPort , SaveFileStreamPort saveFileStreamPort , SaveFileMetaDataPort saveFileMetaDataPort) {
        return new UploadFileService(checkFileValidationsPort , saveFileStreamPort , saveFileMetaDataPort);
    }
    @Bean
    public GetFileUC getFileUC(GetFileMetaDataPort getFileMetaDataPort , GetFilePresidedUrlPort getFilePresidedUrlPort) {
        return new GetFileService(getFileMetaDataPort , getFilePresidedUrlPort);
    }

}
