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
        MinioClient client = MinioClient.builder()
                .credentials(minioProperties.getAccessKey(), minioProperties.getSecretKey())
                .endpoint(System.getenv(minioProperties.getUrl()))
                .build();

        createBucket(client, minioProperties.getBuckets().getAvatars(), true);
        createBucket(client, minioProperties.getBuckets().getChatFiles(), false);

        return client;

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
            throw new RuntimeException("ќшибка создани€ бакета " + bucketName, e);
        }
    }

    private void setPublicPolicy(MinioClient client, String bucketName) throws Exception {
        // JSON-политика: разрешает действие "s3:GetObject" (скачивание) дл€ всех (*)
        // в конкретном бакете по пути "им€-бакета/*"
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

        System.out.println("ѕубличный доступ дл€ бакета '" + bucketName + "' настроен.");
    }
}
