package com.andrew.messenger.configurations;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@ConfigurationProperties(prefix = "minio")
@Configuration
public class MinioProperties {

    private String url;
    private String accessKey;
    private String secretKey;
    private Buckets buckets;

    @Data
    public static class Buckets {
        private String avatars;
        private String chatFiles;
    }
}
