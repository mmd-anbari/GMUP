package org.example.gmup.core.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FileMetaData {
    private Long id ;
    private String originalFilename ;
    private String contentType ;
    private String path ;
}
