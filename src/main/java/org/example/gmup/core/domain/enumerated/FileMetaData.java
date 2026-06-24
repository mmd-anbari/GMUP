package org.example.gmup.core.domain.enumerated;

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
    private Long userId ;
    private String name ;
    private String path ;
    private String type ;
}
