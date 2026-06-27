package org.example.gmup.adapter.outbound.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

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
    private String shortCode ;
    private int downloadCount ;
    private boolean isPublic;
    private LocalDateTime createdAt ;

    @ManyToOne(fetch = FetchType.LAZY)
    private UserEntity user ;
}
