package org.example.gmup.port.outbound.file;

import java.io.InputStream;

public interface SaveFileStreamPort {
    String saveFileStream(String filename, Long userId, InputStream fileStream);
}
