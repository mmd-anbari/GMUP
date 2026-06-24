package org.example.gmup.port.outbound.file;

import java.io.InputStream;

public interface SaveFileStreamPort {
    void saveFileStream(String filename, Long userId, InputStream fileStream);
}
