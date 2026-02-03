package com.andrew.messenger.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ImageService {

    @Value("${app.image.bucket:E:\\Idea-Projects\\messenger\\img\\avatars}")
    private final String bucket;

    @SneakyThrows
    public void upload(String imageName, InputStream inputStream) {
        Path fullImagePath = Path.of(bucket, imageName);
        try(inputStream){
            Files.createDirectories(fullImagePath.getParent());
            Files.write(fullImagePath, inputStream.readAllBytes(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        }
    }

    @SneakyThrows
    public Optional<byte[]> getImage(String imageName) {
        Path fullImagePath = Path.of(bucket, imageName);
        return Files.exists(fullImagePath) ? Optional.of(Files.readAllBytes(fullImagePath)) : Optional.empty();
    }
}
