package com.andrew.messenger.database.mongo;

import java.util.List;

public enum AttachmentType
{
    IMAGE(List.of("jpg", "jpeg", "png", "gif", "webp"), 5 * 1024 * 1024L),
    DOCUMENT(List.of("pdf", "doc", "docx", "txt", "xlsx"), 20 * 1024 * 1024L),
    AUDIO(List.of("mpeg", "wav", "x-wav", "ogg", "aac", "mp4"), 20 * 1024 * 1024L),
    VIDEO(List.of("mp4", "mov", "avi"), 100 * 1024 * 1024L);

    private List<String> allowedExtensions;
    private long maxFileSize;

    AttachmentType(List<String> allowedExtensions, long maxFileSize){
        this.allowedExtensions = allowedExtensions;
        this.maxFileSize = maxFileSize;
    }

    public boolean isAllowedExtension(String extension){
        if(extension == null){
            return false;
        }
        return allowedExtensions.contains(extension.toLowerCase());
    }

    public boolean isAllowedFileSize(long fileSize){
        return fileSize <= maxFileSize;
    }

}
