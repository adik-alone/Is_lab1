package ru.is_lab1.service;

import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.RemoveObjectArgs;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.is_lab1.config.MinioConfig;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import static ru.is_lab1.config.MinioConfig.BUCKET_NAME;
import static ru.is_lab1.config.MinioConfig.ENDPOINT;

@ApplicationScoped
public class MinIOService {
    private static final Logger logger = LoggerFactory.getLogger(MinIOService.class);

    private static final String contentType = "application/json";

    @Inject
    private MinioConfig config;

    private MinioClient client;


    public Pair<String, String> uploadFile(InputStream fileMinio){
        activateClient();
        try{
            String fileId = UUID.randomUUID().toString();
            String objectName = "movie-" + fileId + ".json";

            client.putObject(
                    PutObjectArgs.builder()
                            .bucket(BUCKET_NAME)
                            .object(objectName)
                            .stream(fileMinio, fileMinio.available(), -1)
                            .contentType(contentType)
                            .build()
            );

            String fileUrl = String.format("%s/%s/%s", ENDPOINT, BUCKET_NAME, objectName);
            return Pair.of(fileUrl, objectName);

        } catch (Exception e) {
            logger.error("Error in uploading file", e);
            throw new RuntimeException(e);
        }
    }
    public void deleteFile(String objectName){
        try {
            if (objectName != null) {
                client.removeObject(
                        RemoveObjectArgs.builder()
                                .bucket(BUCKET_NAME)
                                .object(objectName)
                                .build()
                );
            }
        } catch (Exception e) {
            logger.error("Error while deleting file", e);
            throw new RuntimeException(e);
        }
    }
    private void activateClient(){
        if (client == null)
            client = config.produceMinioClient();
    }
}
