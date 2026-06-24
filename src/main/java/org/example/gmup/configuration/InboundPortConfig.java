package org.example.gmup.configuration;

import org.example.gmup.core.service.file.UploadFile;
import org.example.gmup.port.inbound.file.UploadFileUC;
import org.example.gmup.port.outbound.file.SaveFileMetaDataPort;
import org.example.gmup.port.outbound.file.SaveFileStreamPort;
import org.example.gmup.port.outbound.file.CheckFileValidationsPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InboundPortConfig {


    @Bean
    public UploadFileUC uploadFile(CheckFileValidationsPort checkFileValidationsPort , SaveFileStreamPort saveFileStreamPort , SaveFileMetaDataPort saveFileMetaDataPort) {
        return new UploadFile(checkFileValidationsPort , saveFileStreamPort , saveFileMetaDataPort);
    }

}
