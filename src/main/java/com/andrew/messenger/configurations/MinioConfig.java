package com.andrew.messenger.configurations;

import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.SetBucketPolicyArgs;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class MinioConfig {

    private final MinioProperties minioProperties;

    @Bean
    public MinioClient minioClient() {
        return MinioClient.builder()
                .credentials(minioProperties.getAccessKey(), minioProperties.getSecretKey())
                .endpoint(minioProperties.getUrl())
                .build();
    }


    @Bean
    public boolean initBuckets(MinioClient client){
        createBucket(client, minioProperties.getBuckets().getAvatars(), true);
        createBucket(client, minioProperties.getBuckets().getChatFiles(), false);
        return true;
    }

    private void createBucket(MinioClient client, String bucketName, boolean isPublic) {
        try {
            boolean found = client.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
            if (!found) {
                client.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
                if (isPublic) {
                    setPublicPolicy(client, bucketName);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Error to create bucket " + bucketName, e);
        }
    }

    private void setPublicPolicy(MinioClient client, String bucketName) throws Exception {
        String policy = """
        {
            "Version": "2012-10-17",
            "Statement": [
                {
                    "Effect": "Allow",
                    "Principal": "*",
                    "Action": ["s3:GetObject"],
                    "Resource": ["arn:aws:s3:::%s/*"]
                }
            ]
        }
        """.formatted(bucketName);

        client.setBucketPolicy(
                SetBucketPolicyArgs.builder()
                        .bucket(bucketName)
                        .config(policy)
                        .build()
        );

        System.out.println("Public access for bucket " + bucketName + " configured.");
    }
}
