package com.andrew.messenger.dto.message;

import lombok.Data;

import java.util.List;

@Data
public class MessageCreateRequest {
    private Long chatId;
    private String text;
    private List<AttachmentResponseDto> attachments;
}
