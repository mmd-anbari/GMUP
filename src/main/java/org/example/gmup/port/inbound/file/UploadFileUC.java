package org.example.gmup.port.inbound.file;

import org.example.gmup.core.domain.File;
import org.example.gmup.core.dto.FileUploadCommand;

public interface UploadFileUC {

    boolean uploadFile(FileUploadCommand command , long userId);

}
