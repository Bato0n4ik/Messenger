--liquibase formatted sql

--changeset andreev:1
CREATE TABLE IF NOT EXISTS "users" (
                                      id BIGSERIAL  PRIMARY KEY,
                                      username VARCHAR(64) UNIQUE NOT NULL ,
                                      firstname VARCHAR(64),
                                      lastname VARCHAR(64),
                                      birth_date DATE,
                                      role VARCHAR(32),
                                      password VARCHAR(128) DEFAULT '{noop}1234'
);
--DROP

--changeset andreev:2
CREATE TABLE IF NOT EXISTS "chat" (
                                      id BIGSERIAL PRIMARY KEY,
                                      name VARCHAR(64) NOT NULL,
                                      messages VARCHAR(128)
);

--changeset andreev:3
CREATE TABLE IF NOT EXISTS "users_chats" (
                                            id BIGSERIAL PRIMARY KEY,
                                            user_id BIGINT REFERENCES "users" (id),
                                            chat_id BIGINT REFERENCES "chat" (id),
                                            UNIQUE(user_id, chat_id)
);



--changeset andreev:4
CREATE TABLE IF NOT EXISTS "users_friends" (
                                              id BIGSERIAL PRIMARY KEY,
                                              user_id_1 BIGINT REFERENCES "users" (id),
                                              user_id_2 BIGINT REFERENCES "users" (id),
                                              UNIQUE(user_id_1, user_id_2)
);