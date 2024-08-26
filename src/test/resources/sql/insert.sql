INSERT INTO user VALUES(
    (1, 'petya_petuchkov.47@gmail.com', 'Petya' , 'Petychkov', '1990-01-01', 'USER'),
    (2, 'silver_100lone@gmail.com' , 'Selvester', 'Stalone', '1950-05-14', 'ADMIN'),
    (3, 'andruh_98@gmail.com', 'Andrew', 'Andreev', '1998-09-01', 'ADMIN'),
    (4, 'kailkokain.1337@gmail.com', 'Kail', 'Kokain', '2001-03-24', 'USER'),
    (5, '4ik_4irik.43@gmail.com' 'Feodor', 'Begliy', '2005-11-07', 'USER')
);

SELECT SETVAL(user_id_seq, (SELECT MAX(id) FROM user));

INSERT INTO friend VALUES (

);