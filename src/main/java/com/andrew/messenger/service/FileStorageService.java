package com.andrew.messenger.service;

import com.andrew.messenger.configurations.MinioProperties;
import com.andrew.messenger.database.mongo.Attachment;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.errors.MinioException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class FileStorageService {

    private final MinioClient minioClient;
    private final MinioProperties minioProperties;

    public Attachment uploadChatFiles(MultipartFile file, String type) {


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

        return Attachment.builder()
                .fileId(fileId)
                .fileName(objectPath) // it needed to easily get or delete object in future
                .fileSize(file.getSize())
                .type(type)
                .build();

    }

}
