package org.example.gmup.mapper;

import org.example.gmup.adapter.outbound.entity.FileMetaDataEntity;
import org.example.gmup.core.domain.File;
import org.example.gmup.core.domain.enumerated.FileMetaData;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.web.multipart.MultipartFile;

@Mapper(componentModel = "spring")
public interface FileMapper {

    @Mapping(target = "id"  , ignore = true)
    FileMetaDataEntity toEntity(FileMetaData fileMetaData);
    File toDomain(FileMetaDataEntity fileMetaDataEntity);
    File toDomainFromMultipartFile(MultipartFile multipartFile);
}
