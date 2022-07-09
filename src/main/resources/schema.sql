DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS account;
DROP TABLE IF EXISTS account_history;

CREATE TABLE users (
    user_id int NOT NULL DEFAULT 0 AUTO_INCREMENT COMMENT '사용자ID',
    name varchar(20) NOT NULL COMMENT '이름',
    age int NOT NULL COMMENT '나이',
    register_date datetime DEFAULT NULL COMMENT '가입일',
    PRIMARY KEY (user_id)
);

CREATE TABLE account (
    account_id varchar(20) DEFAULT NULL COMMENT '계좌번호',
    user_id int NOT NULL COMMENT '사용자ID',
    PRIMARY KEY (account_id),
    foreign key (user_id) references users(user_id)
);

CREATE TABLE account_history (
    account_id varchar(20) DEFAULT NULL COMMENT '계좌번호',
    depowith_yn char(1) NOT NULL DEFAULT 'Y' COMMENT '입출금여부',
    deposit_amt int NOT NULL DEFAULT 0 COMMENT '입금액',
    deposit_date datetime DEFAULT NULL COMMENT '입금일',
    foreign key (account_id) references account(account_id)
);

INSERT INTO users
(name, age, register_date)
values
    ('Liam', '63', now()),
    ('Loah', '38', now()),
    ('Oliver', '19', now());

INSERT INTO account
(account_id, user_id)
values
    ('1000-01', '1');

INSERT INTO account_history
(account_id, depowith_yn, deposit_amt, deposit_date)
values
    ('1000-01', 'Y', 100000, now()),
    ('1000-01', 'N', 20000, now()),
    ('1000-01', 'N', 30000, now());