package org.example.gmup.adapter.outbound.minio;

import io.minio.GetObjectArgs;
import io.minio.GetPresignedObjectUrlArgs;
import io.minio.MinioClient;
import io.minio.http.Method;
import org.example.gmup.core.domain.FileMetaData;
import org.example.gmup.core.domain.exception.FIleNotExistsException;
import org.example.gmup.port.outbound.file.GetFilePresidedUrlPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.concurrent.TimeUnit;

@Component
public class GetFileTokenMinioAdapter implements GetFilePresidedUrlPort {

    private MinioClient minioClient;
    private static String bucketName = "mybucket";

    @Autowired
    public GetFileTokenMinioAdapter(MinioClient minioClient) {
        this.minioClient = minioClient;
    }


    public InputStream getFileStream(String path) {
        try {
            return minioClient.getObject(
                    GetObjectArgs.
                            builder().
                            bucket(bucketName).
                            object(path).
                            build());
        } catch (Exception e) {

            throw new FIleNotExistsException("file with path " + path + " not found!//from GetFileStreamMinioAdapter");

        }
    }

    @Override
    public String getFilePresidedUrl(FileMetaData fileMetaData) {
        try {
            return minioClient.getPresignedObjectUrl(
                    GetPresignedObjectUrlArgs.
                            builder().
                            object(fileMetaData.getPath()).
                            bucket(bucketName).expiry(10, TimeUnit.MINUTES).
                            method(Method.GET).
                            build());

        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new FIleNotExistsException("file with path " + fileMetaData.getPath() + " not found!//from GetFileStreamMinioAdapter");
        }
    }
}
