package com.andrew.messenger.dto.message;

import com.andrew.messenger.database.mongo.Attachment;
import com.andrew.messenger.database.mongo.MessageType;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class MessageDto {
    private String id;

    private Long senderId;

    private Long chatId;

    private String text;

    private List<AttachmentResponseDto> attachments;

    private MessageType type;

    private LocalDateTime timestamp;
}
