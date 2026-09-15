package org.example.gmup.adapter.outbound.minio;

import io.minio.*;
import io.minio.http.Method;
import jakarta.annotation.PostConstruct;
import org.example.gmup.core.domain.FileMetaData;
import org.example.gmup.core.domain.exception.FIleNotExistsException;
import org.example.gmup.port.outbound.file.GetFilePresignedUploadUrlPort;
import org.example.gmup.port.outbound.file.SaveFileMetaDataPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.concurrent.TimeUnit;

@Component
public class MinioSaver implements GetFilePresignedUploadUrlPort{

    private MinioClient minioClient;
    private static String bucketName = "mybucket";

    @Autowired
    public MinioSaver(MinioClient minioClient) {
        this.minioClient = minioClient;
    }

    @PostConstruct
    public void init() {
        try {
            boolean bucketExists = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
            if (!bucketExists) {
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public String getFileUpUrl(FileMetaData fileMetaData) {
        try {
            return minioClient.getPresignedObjectUrl(
                    GetPresignedObjectUrlArgs.
                            builder().
                            object(fileMetaData.getPath()).
                            bucket(bucketName).expiry(10, TimeUnit.MINUTES).
                            method(Method.PUT).
                            build());

        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new FIleNotExistsException("file with path " + fileMetaData.getPath() + " not found!//from GetFileStreamMinioAdapter");
        }
    }
}
