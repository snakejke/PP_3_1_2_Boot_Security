

INSERT INTO users (firstName, lastName, email, age,password) VALUES ('user', 'user', 'user@example.com',11,'$2a$11$3n87zXotu6hZrxb90Xb80eSAbwsOqdkP2QFsseV6mMene1NQti/my'); -- 111
INSERT INTO users (firstName, lastName, email, age,password) VALUES ('admin', 'admin', 'admin@example.com',66,'$2a$11$BHKzZFMI1bQZup3kKScCw.e2n6JtIJagz43f2iJrsEvxSh5wlDDBG'); -- 333
INSERT INTO users (firstName, lastName, email, age,password) VALUES ('Вася', 'Бобров', 'bobrov@example.com',66,'$2a$11$mMQ1P7K92iBg1MSZcto.SuuRag13DNEdIN4ORjclKMoVL5u6bLxDu'); -- 444
INSERT INTO users (firstName, lastName, email, age) VALUES ('Alice', 'Smith', 'alice@example.com',55);
INSERT INTO users (firstName, lastName, email, age) VALUES ('Андрей', 'Щукин', 'shuka@example.com',11);
INSERT INTO users (firstName, lastName, email, age) VALUES ('Дима', 'Иванов', 'ivanov@example.com',11);
INSERT INTO users (firstName, lastName, email, age) VALUES ('Michael', 'Brown', 'michael@example.com',44);

INSERT INTO `roles` (`name`) VALUES ('USER');
INSERT INTO `roles` (`name`) VALUES ('ADMIN');

INSERT INTO `users_roles` (`USER_ID`, `ROLE_ID`) VALUES (1, 1);
INSERT INTO `users_roles` (`USER_ID`, `ROLE_ID`) VALUES (2, 1),(2, 2);


