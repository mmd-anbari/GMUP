package org.example.gmup.core.service.file;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.example.gmup.core.domain.File;
import org.example.gmup.port.inbound.file.UploadFileUC;
import org.example.gmup.port.outbound.file.CheckFileValidationsPort;
import org.example.gmup.port.outbound.file.SaveFileMetaDataPort;
import org.example.gmup.port.outbound.file.SaveFileStreamPort;

@AllArgsConstructor
@NoArgsConstructor
public class UploadFile implements UploadFileUC {

    private CheckFileValidationsPort checkFileValidationsPort;
    private SaveFileStreamPort saveFileStreamPort;
    private SaveFileMetaDataPort saveFileMetaDataPort;


    @Override
    public boolean uploadFile(File file) {
        if(checkFileValidationsPort.isDuplicatedFileName(file.getFileMetaData().getName())){
            return false;
        }

        saveFileMetaDataPort.saveMetaData(file.getFileMetaData());

        saveFileStreamPort.saveFileStream(
                file.getFileMetaData().getName(),
                file.getFileMetaData().getUserId(),
                file.getStream());

        return true;
    }

}
