package org.example.gmup.adapter.inbound.dto.file;

import java.io.InputStream;

public record FileMetaDataUploadDto(String originalFilename,
                                    String contentType,
                                    long size) {
}
