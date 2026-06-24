package org.example.gmup.adapter.inbound.dto.file;

import java.io.InputStream;

public record MultipartFileToFile(Long userId,
                                  String name,
                                  String type,
                                  InputStream stream) {
}
