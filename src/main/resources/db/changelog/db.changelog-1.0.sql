--liquibase formatted sql

--changeset andreev:1
CREATE TABLE IF NOT EXISTS "user" (
                                      id BIGSERIAL  PRIMARY KEY,
                                      username VARCHAR(64) UNIQUE NOT NULL ,
                                      firstname VARCHAR(64),
                                      lastname VARCHAR(64),
                                      birth_date DATE,
                                      role VARCHAR(32),
                                      password VARCHAR(128) DEFAULT '{noop}1234',
                                      email VARCHAR(128)
);
--DROP

--changeset andreev:2
CREATE TABLE IF NOT EXISTS "chat" (
                                      id BIGSERIAL PRIMARY KEY,
                                      name VARCHAR(64) NOT NULL UNIQUE,
                                      massage VARCHAR(128)
);

--changeset andreev:3
CREATE TABLE IF NOT EXISTS "user_chats" (
                                            id BIGSERIAL PRIMARY KEY,
                                            user_id BIGINT REFERENCES "user" (id),
                                            chat_id BIGINT REFERENCES "chat" (id),
                                            UNIQUE(user_id, chat_id)
);

--changeset andreev:4
CREATE TABLE IF NOT EXISTS "friend" (
                                        id SERIAL PRIMARY KEY,
                                        user_id BIGINT UNIQUE NOT NULL REFERENCES "user" (id)
);

--changeset andreev:5
CREATE TABLE IF NOT EXISTS "user_friends" (
                                              id BIGSERIAL PRIMARY KEY,
                                              user_id BIGINT REFERENCES "user" (id),
                                              friend_id BIGINT REFERENCES "friend" (id),
                                              UNIQUE(user_id, friend_id)
);