package com.andrew.messenger.service;

import com.andrew.messenger.database.mongo.Message;
import com.andrew.messenger.database.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;

    public void save(Message message) {
        messageRepository.save(message);
    }
}
