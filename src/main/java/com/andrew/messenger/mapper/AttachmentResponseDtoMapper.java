package com.andrew.messenger.mapper;

import com.andrew.messenger.configurations.MinioProperties;
import com.andrew.messenger.database.mongo.Attachment;
import com.andrew.messenger.dto.message.AttachmentResponseDto;
import io.minio.GetPresignedObjectUrlArgs;
import io.minio.Http;
import io.minio.MinioClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
@Slf4j
public class AttachmentResponseDtoMapper implements Mapper<Attachment, AttachmentResponseDto>{

    private final MinioClient minioClient;
    private final MinioProperties minioProperties;

    @Override
    public AttachmentResponseDto map(Attachment from) {
        return AttachmentResponseDto.builder()
                .fileId(from.getFileId())
                .fileName(from.getFileName())
                .fileSize(from.getFileSize())
                .type(from.getType())
                .url(generatedUrl(from.getFileName()))
                .build();
    }

    public String generatedUrl(String objectPath) {
        try {
            return minioClient.getPresignedObjectUrl(
                    GetPresignedObjectUrlArgs.builder()
                            .method(Http.Method.GET)
                            .bucket(minioProperties.getBuckets().getChatFiles())
                            .object(objectPath)
                            .expiry(15, TimeUnit.MINUTES)
                            .build()
            );
        } catch (Exception e) {
            log.error("Can't generate url: {}", e.getMessage());
            return null;
        }
    }
}
