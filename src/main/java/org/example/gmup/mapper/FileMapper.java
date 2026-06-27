package org.example.gmup.mapper;

import org.example.gmup.adapter.inbound.dto.file.FileMenu;
import org.example.gmup.adapter.outbound.entity.FileMetaDataEntity;
import org.example.gmup.core.domain.File;
import org.example.gmup.core.domain.FileMetaData;
import org.example.gmup.core.dto.FileUploadCommand;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.web.multipart.MultipartFile;

@Mapper(componentModel = "spring")
public interface FileMapper {

    @Mapping(target = "id"  , ignore = true)
    FileMetaDataEntity toEntity(FileMetaData fileMetaData);
    FileMetaData toDomain(FileMetaDataEntity fileMetaDataEntity);
    File toDomainFromMultipartFile(MultipartFile multipartFile);
    FileMetaData fromMultipartFileToFileMetaData(MultipartFile multipartFile);
    FileMenu fromFileToFileMenu(File file);


}
