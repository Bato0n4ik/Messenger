package com.andrew.messenger.database.mongo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "messages")
@CompoundIndex(name = "chat_history_idx", def="{chatId: 1, timestamp: -1}")
public class Message {

    @Id
    private String id;

    @Indexed
    private Long senderId;

    private Long chatId;

    private String text;

    private List<Attachment> attachments; // Список вложений

    private MessageType type; // ENUM: TEXT, IMAGE, FILE, MIXED

    private LocalDateTime timestamp;
}
