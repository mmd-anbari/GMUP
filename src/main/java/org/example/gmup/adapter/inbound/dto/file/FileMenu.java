package org.example.gmup.adapter.inbound.dto.file;

import java.io.InputStream;
import java.time.LocalDateTime;

public record FileMenu(
        String originalFilename,
        String contentType,
        String token,
        String shortCode,
        int downloadCount,
        boolean isPublic,
        LocalDateTime createdAt

) {
}
