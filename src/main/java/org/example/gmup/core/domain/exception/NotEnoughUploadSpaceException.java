package org.example.gmup.core.domain.exception;

public class NotEnoughUploadSpaceException extends RuntimeException {
    public NotEnoughUploadSpaceException(String message) {
        super(message);
    }
}
