package ru.is_lab1.config;

import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.Produces;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
@Getter
public class MinioConfig {
    private static final Logger logger = LoggerFactory.getLogger(MinioConfig.class);

    private MinioClient minioClient;

    public static final String ENDPOINT = "http://localhost:9000";
    private static final String accessKey = "admin";
    private static final String secretKey = "password";
    public static final String BUCKET_NAME = "movies-file";

    @PostConstruct
    public void init(){
        logger.info("MinIO initializing...");
        System.out.println(ENDPOINT);
        try{
            minioClient = MinioClient.builder()
                    .endpoint(ENDPOINT)
                    .credentials(accessKey, secretKey)
                    .build();

            minioClient.listBuckets();

            createBucketIfNotExists(BUCKET_NAME);

            logger.info("MinIO initialized successfully");
        }catch (Exception e){
            logger.error("Error in MinIO initializing:",e);
            throw new RuntimeException(e);
        }
    }

    private void createBucketIfNotExists(String bucketName){
        logger.info("Check bucket");
        try{
            boolean exists = minioClient.bucketExists(
                    BucketExistsArgs.builder()
                            .bucket(bucketName)
                            .build()
            );
            if (!exists){
                minioClient.makeBucket(
                        MakeBucketArgs.builder()
                                .bucket(bucketName)
                                .build()
                );
                logger.info("Bucket created");
            }else{
                logger.info("Bucket exists");
            }
        }catch (Exception e){
            logger.error("Error in checking bucket", e);
        }
    }

    @Produces
    @ApplicationScoped
    public MinioClient produceMinioClient(){
        if (minioClient == null)
            throw new IllegalStateException("MinIO client didn't initialize");
        return minioClient;
    }
}
