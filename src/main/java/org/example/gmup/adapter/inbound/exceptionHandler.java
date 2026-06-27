package org.example.gmup.adapter.inbound;

import org.example.gmup.adapter.inbound.exception.AccessDeniedException;
import org.example.gmup.adapter.inbound.exception.StorageProblemException;
import org.example.gmup.core.domain.exception.FIleNotExistsException;
import org.example.gmup.core.domain.exception.FileDownloadAccessDeniedException;
import org.example.gmup.core.domain.exception.UserNameAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class exceptionHandler {

    @ExceptionHandler(FIleNotExistsException.class)
    public ResponseEntity<String> handleFileNotExistsException(FIleNotExistsException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(FileDownloadAccessDeniedException.class)
    public ResponseEntity<String> handleAccessDeniedException(AccessDeniedException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(UserNameAlreadyExistsException.class)
    public ResponseEntity<String> handleUserNameAlreadyExistsException(UserNameAlreadyExistsException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
    }
}
