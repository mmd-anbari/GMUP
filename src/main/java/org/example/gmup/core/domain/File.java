package org.example.gmup.core.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.InputStream;

@Getter
@Setter
@NoArgsConstructor
public class File {

    private Long userId ;
    private FileMetaData fileMetaData;
    private InputStream stream ;

}
