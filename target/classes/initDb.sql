CREATE TABLE user
(
    userId         INTEGER PRIMARY KEY ASC,
    nickname       varchar(25),
    dateRegistered TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    dateOfBirth    VARCHAR(25),
    about          VARCHAR(250)
);

--NEXT--

CREATE TABLE tweet
(
    tweetId        INTEGER PRIMARY KEY ASC,
    userId         INTEGER     NOT NULL,
    referenceTweet INTEGER,
    datePosted     TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    content        VARCHAR(25) NOT NULL,
    FOREIGN KEY (userId) REFERENCES user (userId)
);

--NEXT--

CREATE TABLE followers
(
    userId          INTEGER NOT NULL,
    followingUserId INTEGER NOT NULL,
    since           TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT UNIQUE_COMBINATION UNIQUE (userId, followingUserId)
);

--NEXT--

CREATE TABLE likes
(
    tweetId     INTEGER NOT NULL,
    likedUserId INTEGER NOT NULL,
    linkedAt    TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT UNIQUE_COMBINATION UNIQUE (tweetId, likedUserId)
);