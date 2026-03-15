package com.andrew.messenger.database.repository;

import com.andrew.messenger.database.mongo.Message;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MessageRepository extends MongoRepository<Message, String> {
}
