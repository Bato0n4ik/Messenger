package com.andrew.messenger.database.mongo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collation = "messages")
@CompoundIndex(name = "chat_history_idx", def="{chatId: 1, timestamp: -1}")
public class Message {

    @Id
    private String id;

    @Indexed(unique = true)
    private Long senderId;

    private Long chatId;

    private String text;

    private LocalDateTime timestamp;
}
