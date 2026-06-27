package org.example.gmup.core.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FileMetaData {
    private Long id ;
    private String originalFilename ;
    private String contentType ;
    private String shortCode ;
    private int downloadCount ;
    private boolean isPublic;
    private LocalDateTime createdAt ;
    private String path ;
}
