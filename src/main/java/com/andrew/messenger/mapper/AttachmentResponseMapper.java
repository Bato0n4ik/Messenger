package com.andrew.messenger.mapper;

import com.andrew.messenger.database.mongo.Attachment;
import com.andrew.messenger.dto.message.AttachmentResponseDto;
import org.springframework.stereotype.Component;

@Component
public class AttachmentResponseMapper implements Mapper<Attachment, AttachmentResponseDto>{

    private final String DUMMY_URL = "dummy_url";
    @Override
    public AttachmentResponseDto map(Attachment from) {

        return AttachmentResponseDto.builder()
                .fileId(from.getFileId())
                .fileName(from.getFileName())
                .fileSize(from.getFileSize())
                .type(from.getType())
                .url(DUMMY_URL)
                .build();
    }


}
