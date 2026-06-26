package org.example.gmup.adapter.inbound.dto.file;

import java.io.InputStream;

public record FileMenu (
        String originalFilename ,
        String contentType ,
        String token

) {
}
