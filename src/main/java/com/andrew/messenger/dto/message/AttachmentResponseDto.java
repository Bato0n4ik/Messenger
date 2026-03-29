package com.andrew.messenger.dto.message;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AttachmentResponseDto {
    private String fileId;
    private String type;
    private String fileName;
    private Long fileSize;
    private String url;
}
