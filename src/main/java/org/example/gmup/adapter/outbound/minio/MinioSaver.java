package org.example.gmup.adapter.outbound.minio;

import io.minio.*;
import jakarta.annotation.PostConstruct;
import org.example.gmup.port.outbound.file.SaveFileStreamPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.InputStream;

@Component
public class MinioSaver implements SaveFileStreamPort {

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
    public void saveFileStream(String filename, Long userId, InputStream fileStream) {
        String objectName = userId+"/"+filename;
        try {
            minioClient.putObject(
                    PutObjectArgs.builder().
                    stream(fileStream,-1, 10485760).
                    bucket(bucketName).object(objectName).
                    build());
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
