--liquibase formatted sql

--changeset andrew:1
ALTER TABLE chat DROP COLUMN messages;

--changeset andrew:2
ALTER TABLE chat ADD COLUMN created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP;

--changeset andrew:2
ALTER TABLE chat ADD COLUMN chat_type VARCHAR(20);

