package org.example.gmup.core.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.InputStream;

public record FileUploadCommand(
        String originalFilename,
        String contentType,
        InputStream inputStream,
        boolean isPublic

) {


}
