package org.example.gmup.core.dto;

public record FileMenuCommand(
        String originalFilename,
        String contentType
) {
}
