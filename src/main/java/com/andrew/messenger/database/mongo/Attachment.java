package com.andrew.messenger.database.mongo;

public class Attachment {

    private String fileId;   // ID файла в вашем хранилище (S3, GridFS и т.д.)
    private String type;     // IMAGE, GIF, DOCUMENT, VIDEO
    private String fileName; // Исходное имя файла
    private Long fileSize;   // Размер в байтах
    private String url;      // Ссылка для доступа (если нужно)
}
