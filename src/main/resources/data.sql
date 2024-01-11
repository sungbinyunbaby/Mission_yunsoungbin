DELETE FROM boards_entity WHERE id BETWEEN 5 AND 8;

INSERT INTO boards_entity(boards)
VALUES
    ('FreeBoard'),
    ('DevelopBoard'),
    ('IncidentBoard'),
    ('PhotoBoard');

INSERT INTO articles_entity(title, content, password, boards_entity_id)
VALUES
    ('안녕하세요', '내용12334', 1234, 1),
    ('이거 왜 이러냐', 'content2', 1234, 1),
    ('미친거니', 'content3', 1234, 1);

SELECT * FROM articles_entity ORDER BY id DESC ;

INSERT INTO comments_entity(comment, password, articles_entity_and_comment_entity_id)
VALUES
    ('ㅋㅋㅋ이거 나오면 성공한거다', 1234, 3),
    ('쨘', 1234, 3),
    ('축배를 들라', 1234, 3);

INSERT INTO comments_entity(comment, password, articles_entity_and_comment_entity_id)
VALUES
    ('ㅋㅋㅋ이거 나오면 성공한거다 2번', 1234, 2),
    ('쨘 2번', 1234, 2),
    ('축배를 들라 1번', 1234, 1);

ALTER TABLE comments_entity DROP articles_entity_id;