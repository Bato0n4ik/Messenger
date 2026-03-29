package com.andrew.messenger.validation;

import com.andrew.messenger.database.mongo.AttachmentType;
import org.apache.tika.Tika;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Component
public class FileValidator {

    private final Tika tika = new Tika();

    public boolean isRealContentValid(MultipartFile file, AttachmentType type) {
        try {
            String detectedType = tika.detect(file.getInputStream());

            return switch (type) {
                case IMAGE -> detectedType.startsWith("image/");
                case VIDEO -> detectedType.startsWith("video/");
                case DOCUMENT -> detectedType.startsWith("application/pdf") ||
                        detectedType.startsWith("text/") ||
                        detectedType.contains("officedocument");
                case AUDIO -> detectedType.startsWith("audio/");
                default -> false;
            };
        } catch (IOException e) {
            return false;
        }
    }
}
