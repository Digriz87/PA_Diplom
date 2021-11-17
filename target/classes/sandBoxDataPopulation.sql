INSERT INTO user (nickname, dateOfBirth, about)
VALUES ('Bilbo', '1993-09-05', 'I was first of owner ring')
     , ('Frodo', '1990-09-02', 'I`m second of owner ring')
     , ('Gandalf', '1985-10-10', 'He wore a tall pointed blue hat, a long grey cloak, and a silver scarf')
     , ('Sauron', '1987-12-02', 'eponymous Lord of the Rings, originally called Mairon');

--NEXT--

INSERT INTO tweet (userId, datePosted, referenceTweet, content)
VALUES (1, '2021-09-05', null, 'My precious!')
     , (1, null, null, 'SMEE-AH-GOL');

--NEXT--

INSERT INTO tweet (userId, datePosted, referenceTweet, content)
VALUES (2, '2020-09-05', 1, 'I will take the Ring, though I do not know the way')
     , (2, null, null, 'This is my own precious');

--NEXT--

INSERT INTO tweet (userId, datePosted, referenceTweet, content)
VALUES (3, '2019-09-05', 3, 'Then darkness took me, and I strayed out of thought and time')
     , (3, null, null, 'Keep well the Lord of the Mark, till I return. Await me at Helm''s Gate. Farewell!');

--NEXT--

INSERT INTO tweet (userId, datePosted, referenceTweet, content)
VALUES (4, '2020-09-05', 1, 'You cannot hide. I see you. There is no life in the void, only death')
     , (4, null, null, 'It has begun. The East will fall. So shall the Kingdom of Angmar rise');

--NEXT--

INSERT INTO followers (userId, followingUserId)
VALUES (1, 2)
     , (1, 3)
     , (1, 4)
     , (2, 1)
     , (2, 3)
     , (2, 4)
     , (3, 1)
     , (4, 2);

--NEXT--

INSERT INTO likes (tweetId, likedUserId)
VALUES (1, 2)
     , (1, 3)
     , (1, 4)
     , (2, 1)
     , (2, 3)
     , (2, 4)
     , (3, 1)
     , (4, 2);