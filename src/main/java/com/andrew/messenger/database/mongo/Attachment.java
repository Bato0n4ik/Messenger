package com.andrew.messenger.database.mongo;

public class Attachment {

    private String fileId;   // ID  (S3, GridFS)
    private String type;     // IMAGE, GIF, DOCUMENT, VIDEO
    private String fileName;
    private Long fileSize;
    //private String url;
}
