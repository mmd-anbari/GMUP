package org.example.gmup.adapter.inbound;

import org.example.gmup.adapter.inbound.exception.AccessDeniedException;
import org.example.gmup.adapter.inbound.exception.StorageProblemException;
import org.example.gmup.core.domain.exception.FIleNotExistsException;
import org.example.gmup.core.domain.exception.FileDownloadAccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class exceptionHandler {

    @ExceptionHandler(FIleNotExistsException.class)
    public StorageProblemException handleFileNotExistsException(FIleNotExistsException e) {
        return new StorageProblemException(e.getMessage());
    }

    @ExceptionHandler(FileDownloadAccessDeniedException.class)
    public StorageProblemException handleAccessDeniedException(AccessDeniedException e) {
        return new StorageProblemException(e.getMessage());
    }
}
