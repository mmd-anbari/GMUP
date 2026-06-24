package org.example.gmup.core.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.gmup.core.domain.enumerated.FileMetaData;

import java.io.InputStream;

@Getter
@Setter
@NoArgsConstructor
public class File {

    private FileMetaData fileMetaData;
    private InputStream stream ;

}
