package com.andrew.messenger.service;

import com.andrew.messenger.configurations.MinioProperties;
import io.minio.GetObjectArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.errors.ErrorResponseException;
import io.minio.errors.MinioException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Slf4j
public class AvatarService {

    private final MinioClient minioClient;
    private final MinioProperties minioProperties;

    public void uploadAvatar(String fileName, byte[] image) {

        String objectPath = "jpg" + "/" + fileName;

        try{
            minioClient.putObject(PutObjectArgs.builder()
                    .bucket(minioProperties.getBuckets().getAvatars())
                    .object(objectPath)
                    .stream(new ByteArrayInputStream(image),(long)image.length,-1L)
                    .contentType(MediaType.IMAGE_JPEG_VALUE)
                    .build()
            );
        }
        catch(MinioException exc){
            log.error(exc.getMessage());
            throw new RuntimeException(exc);
        }
    }



    public Optional<byte[]> getAvatar(String fileName) {

        String objectPath = "jpg" + "/" + fileName;

        try(InputStream avatarStream = minioClient.getObject(GetObjectArgs.builder()
                .bucket(minioProperties.getBuckets().getAvatars())
                .object(objectPath)
                .build())){

            return Optional.of(IOUtils.toByteArray(avatarStream));
        }
        catch (ErrorResponseException exc){
            if("NoSuchKey".equals(exc.errorResponse().code())){
                log.warn("Avatar is not found: {}", objectPath);
                return Optional.empty();
            }
            throw new RuntimeException("Error Minio", exc);
        }
        catch(MinioException | IOException exc){
            log.error("Critical error loading avatar: {}",exc.getMessage());
            return Optional.empty();
        }
    }
}
