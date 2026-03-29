package com.andrew.messenger.service;

import com.andrew.messenger.configurations.MinioProperties;
import com.andrew.messenger.dto.message.AttachmentResponseDto;
import io.minio.GetPresignedObjectUrlArgs;
import io.minio.Http;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.errors.MinioException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
@Slf4j
public class FileStorageService {

    private final MinioClient minioClient;
    private final MinioProperties minioProperties;

    public AttachmentResponseDto upload(MultipartFile file, String type) {


        String fileId = UUID.randomUUID() + "." + type;
        String objectPath = type + "/" + file.getOriginalFilename();

        try{
            minioClient.putObject(PutObjectArgs.builder()
                    .bucket(minioProperties.getBuckets().getChatFiles())
                    .object(objectPath)
                    .stream(file.getInputStream(), file.getSize(), -1L)
                    .contentType(file.getContentType())
                    .build());
        }
        catch(IOException | MinioException exc){
            log.error(exc.getMessage());
            throw new RuntimeException(exc);
        }

        //Attachment attachment = new Attachment();
        //attachment.setFileId(fileId);
        //attachment.setFileName(objectPath);
        //attachment.setType(type);
        //attachment.setFileSize(file.getSize());


        return AttachmentResponseDto.builder()
                .fileId(fileId)
                .fileName(objectPath) // it needed to easily get or delete object in future
                .fileSize(file.getSize())
                .type(type)
                .url(generatedUrl(objectPath))
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
