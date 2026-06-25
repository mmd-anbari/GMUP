package org.example.gmup.adapter.outbound.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter

public class FileMetaDataEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    private String originalFilename ;
    private String contentType ;
    private String path ;

    @ManyToOne
    private UserEntity user ;
}
