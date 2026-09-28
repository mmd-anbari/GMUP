package org.example.gmup.core.domain.exception;

public class DuplicatedFileNameException extends RuntimeException {
    public DuplicatedFileNameException(String message) {
        super(message);
    }
}
