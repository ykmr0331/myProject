DROP PROCEDURE IF EXISTS testDataInsert;

DELIMITER $$

CREATE PROCEDURE testDataInsert()
BEGIN
    DECLARE i INT DEFAULT 1;

    WHILE i <= 120 DO
        INSERT INTO board(title, content,member_id, secret)
          VALUES(CONCAT('제목', i), CONCAT('내용', i),1, 1);
        SET i = i + 1;
    END WHILE;
END$$

DELIMITER ;

CALL testDataInsert;

-- user2는 비밀글 2개 공개글 2개 만들것임
INSERT INTO board(title, content,member_id, secret) VALUES('user2 비밀글 제목 1','user2 비밀글 내용 1',2, 1);
INSERT INTO board(title, content,member_id, secret) VALUES('user2 비밀글 제목 2','user2 비밀글 내용 2',2, 1);
INSERT INTO board(title, content,member_id, secret) VALUES('user2 공개글 제목 1','user2 공개글 내용 1',1, 0);
INSERT INTO board(title, content,member_id, secret) VALUES('user2 공개글 제목 2','user2 공개글 내용 2',1, 0);