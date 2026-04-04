package com.andrew.messenger.dto.message;

import com.andrew.messenger.database.mongo.MessageType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class MessageResponseDto {
    private String id;
    private Long senderId;
    private Long chatId;
    private String text;
    private List<AttachmentResponseDto> attachments;
    private MessageType type;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime timestamp;


    //private boolean isMe;
}
