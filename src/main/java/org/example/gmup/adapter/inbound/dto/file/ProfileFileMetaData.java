package org.example.gmup.adapter.inbound.dto.file;

import java.time.LocalDateTime;

public record ProfileFileMetaData(
        String fileName ,
        String originalFilename,
        String contentType,
        String shortCode,
        int downloadCount,
        boolean isPublic,
        LocalDateTime createdAt
) {
}
