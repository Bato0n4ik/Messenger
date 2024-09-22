INSERT INTO users (id, username, firstname, lastname, birth_date, role) VALUES
    (1, 'petya_petuchkov.47@gmail.com', 'Petya' , 'Petychkov', '1990-01-01', 'USER'),
    (2, 'silver_100lone@gmail.com' , 'Selvester', 'Stalone', '1950-05-14', 'ADMIN'),
    (3, 'andruh_98@gmail.com', 'Andrew', 'Andreev', '1998-09-01', 'ADMIN'),
    (4, 'kailkokain.1337@gmail.com', 'Kail', 'Kokain', '2001-03-24', 'USER'),
    (5, '4ik_4irik.43@gmail.com', 'Fedor', 'Begliy', '2005-11-07', 'USER'),
    (6,'sanya_zub.37@gmail.com', 'Alexander', 'Zubarev', '1987-02-18', 'USER'),
    (7, 'jon_snow.555@gmail.com', 'Jon', 'Snow', '1989-12-09', 'USER');

SELECT SETVAL('users_id_seq', (SELECT MAX(id) FROM users));

INSERT INTO users_friends VALUES
    (1, (SELECT id FROM users WHERE username = 'petya_petuchkov.47@gmail.com'), (SELECT id FROM users WHERE username = 'silver_100lone@gmail.com')),
    (2, (SELECT id FROM users WHERE username = 'petya_petuchkov.47@gmail.com'), (SELECT id FROM users WHERE username = '4ik_4irik.43@gmail.com')),
    (3, (SELECT id FROM users WHERE username = 'silver_100lone@gmail.com'), (SELECT id FROM users WHERE username = 'petya_petuchkov.47@gmail.com')),
    (4, (SELECT id FROM users WHERE username = '4ik_4irik.43@gmail.com'), (SELECT id FROM users WHERE username = 'petya_petuchkov.47@gmail.com')),
    (5, (SELECT id FROM users WHERE username = '4ik_4irik.43@gmail.com'), (SELECT id FROM users WHERE username = 'kailkokain.1337@gmail.com')),
    (6, (SELECT id FROM users WHERE username = 'kailkokain.1337@gmail.com'), (SELECT id FROM users WHERE username = '4ik_4irik.43@gmail.com')),
    (7, (SELECT id FROM users WHERE username = 'andruh_98@gmail.com'), (SELECT id FROM users WHERE username = 'silver_100lone@gmail.com')),
    (8, (SELECT id FROM users WHERE username = 'silver_100lone@gmail.com'), (SELECT id FROM users WHERE username = 'andruh_98@gmail.com'));

SELECT SETVAL('users_friends_id_seq', (SELECT MAX(id) FROM users_friends));

INSERT INTO chat VALUES
    (1, 'One-to-Two', 'E:\Idea-Projects\chats\1\One-to-Two'),
    (2, 'One-to-Five', 'E:\Idea-Projects\chats\2\One-to-Five'),
    (3, 'Two-to-Three', 'E:\Idea-Projects\chats\3\Two-to-Three'),
    (4, 'Five-to-Fore', 'E:\Idea-Projects\chats\4\Five-to-Fore'),
    (5, 'Three-to-Six', 'E:\Idea-Projects\chats\5\Three-to-Six'),
    (6, 'Three_create_group_chat', 'E:\Idea-Projects\chats\6\group_chat'),
    (7, 'Six-to-One', 'E:\Idea-Projects\chats\7\Six-to-One'),
    (8, 'Seven-to-Three', 'E:\Idea-Projects\chats\8\Seven-to-Three'),
    (9, 'Seven-to-Six', 'E:\Idea-Projects\chats\9\Seven-to-Six');

SELECT SETVAL('chat_id_seq', (SELECT MAX(id) FROM chat));

INSERT INTO users_chats (id, user_id, chat_id) VALUES
    (1, (SELECT id FROM users WHERE username = 'petya_petuchkov.47@gmail.com'), (SELECT id FROM chat WHERE name = 'One-to-Two')),
    (2, (SELECT id FROM users WHERE username = 'petya_petuchkov.47@gmail.com'), (SELECT id FROM chat WHERE name = 'One-to-Five')),
    (3, (SELECT id FROM users WHERE username = 'silver_100lone@gmail.com'), (SELECT id FROM chat WHERE name = 'One-to-Two')),
    (4, (SELECT id FROM users WHERE username = '4ik_4irik.43@gmail.com'), (SELECT id FROM chat WHERE name = 'One-to-Five')),
    (5, (SELECT id FROM users WHERE username = 'silver_100lone@gmail.com'), (SELECT id FROM chat WHERE name = 'Two-to-Three')),
    (6, (SELECT id FROM users WHERE username = 'andruh_98@gmail.com'), (SELECT id FROM chat WHERE name = 'Two-to-Three')),
    (7, (SELECT id FROM users WHERE username = '4ik_4irik.43@gmail.com'), (SELECT id FROM chat WHERE name = 'Five-to-Fore')),
    (8, (SELECT id FROM users WHERE username = 'kailkokain.1337@gmail.com'), (SELECT id FROM chat WHERE name = 'Five-to-Fore')),
    (9, (SELECT id FROM users WHERE username = 'andruh_98@gmail.com'), (SELECT id FROM chat WHERE name = 'Three-to-Six')),
    (10, (SELECT id FROM users WHERE username = 'sanya_zub.37@gmail.com'), (SELECT id FROM chat WHERE name = 'Three-to-Six')),
    (11, (SELECT id FROM users WHERE username = 'andruh_98@gmail.com'), (SELECT id FROM chat WHERE name = 'Three_create_group_chat')),
    (12, (SELECT id FROM users WHERE username = 'petya_petuchkov.47@gmail.com'), (SELECT id FROM chat WHERE name = 'Three_create_group_chat')),
    (13, (SELECT id FROM users WHERE username = '4ik_4irik.43@gmail.com'), (SELECT id FROM chat WHERE name = 'Three_create_group_chat')),
    (14, (SELECT id FROM users WHERE username = 'sanya_zub.37@gmail.com'), (SELECT id FROM chat WHERE name = 'Six-to-One')),
    (15, (SELECT id FROM users WHERE username = 'petya_petuchkov.47@gmail.com'), (SELECT id FROM chat WHERE name = 'Six-to-One')),
    (16, (SELECT id FROM users WHERE username = 'jon_snow.555@gmail.com'), (SELECT id FROM chat WHERE name = 'Seven-to-Three')),
    (17, (SELECT id FROM users WHERE username = 'andruh_98@gmail.com'), (SELECT id FROM chat WHERE name = 'Seven-to-Three')),
    (18, (SELECT id FROM users WHERE username = 'jon_snow.555@gmail.com'), (SELECT id FROM chat WHERE name = 'Seven-to-Six')),
    (19, (SELECT id FROM users WHERE username = 'sanya_zub.37@gmail.com'), (SELECT id FROM chat WHERE name = 'Seven-to-Six'));

SELECT SETVAL('users_chats_id_seq', (SELECT MAX(id) FROM users_chats));

