package org.example.gmup.core.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import okio.FileMetadata;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FileDownloadWithToken {

    private FileMetaData fileMetadata;
    private String token ;

}
