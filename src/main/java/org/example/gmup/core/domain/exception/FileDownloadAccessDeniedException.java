package org.example.gmup.core.domain.exception;

public class FileDownloadAccessDeniedException extends RuntimeException {
    public FileDownloadAccessDeniedException(String message) {
        super(message);
    }
}
