package pa.pro.pa_diplom.persistance.jdbc;

import pa.pro.pa_diplom.model.Tweet;
import pa.pro.pa_diplom.model.User;
import pa.pro.pa_diplom.persistance.TweetDao;
import pa.pro.pa_diplom.persistance.TweetDaoInMemImpl;
import pa.pro.pa_diplom.persistance.UserDao;
import pa.pro.pa_diplom.persistance.UserDaoInMemImpl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

public  class DbUtils {

    private static final String TWITTER_DB_URL = "jdbc:sqlite:twitter_db";
    public static final String USER = "user";
    public static final String TWEET = "tweet";
    public static final String LIKES = "likes";
    public static final String FOLLOWERS = "followers";
    public static final String QUERY_DELIMITER = "--NEXT--";
    public static final String getLastId_USER = "SELECT MAX(userId) FROM user";
    public static final String DELETE_USER = "DELETE FROM user WHERE userId=?";
    public static final String FIND_ALL_USER = "SELECT * FROM user ORDER BY userId";
    public static final String FIND_BY_ID_USER = "SELECT * FROM user WHERE userId=?";
    public static final String INSERT_USER = "INSERT INTO user(nickname, dateRegistered, dateOfBirth, about) VALUES(?, ?, ?, ?)";
    public static final String UPDATE_USER = "UPDATE user SET nickname=?, dateRegistered=?, dateOfBirth=?, about=? WHERE userId=?";
    public static final String getLastId_TWEET = "SELECT MAX(tweetId) FROM tweet";
    public static final String DELETE_TWEET = "DELETE FROM tweet WHERE tweetId=?";
    public static final String FIND_ALL_TWEET = "SELECT * FROM tweet ORDER BY tweetId";
    public static final String FIND_BY_ID_TWEET = "SELECT * FROM tweet WHERE tweetId=?";
    public static final String INSERT_TWEET = "INSERT INTO tweet(userId, referenceTweet, datePosted, content) VALUES(?, ?, ?, ?)";
    public static final String UPDATE_TWEET = "UPDATE tweet SET referenceTweet=?, datePosted=?, content=? WHERE tweetId=?";


    private static final List<String> TABLES = Arrays.asList(USER, TWEET, LIKES, FOLLOWERS);

   static void closeResource(ResultSet rs, PreparedStatement statement1, AutoCloseable resource) {
        if (resource != null) {
              try {
                    resource.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
          }
   }

    private static Logger log = Logger.getLogger(DbUtils.class.getName());

    public static  Connection getConnection() {
        return getConnection(TWITTER_DB_URL);
    }

    private static  Connection getConnection(String twitterDbUrl) {
        try {
            return DriverManager.getConnection(twitterDbUrl);
        } catch (SQLException throwable) {
            throwable.printStackTrace();
            return null;
        }
    }

    public static void closeConnection(Connection connection) {
        try {
            connection.close();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    public static void main(String[] args) {
        initDb();

    }

    public static void initDb() {
        log.info("Checking if DB initiated...");
        if (checkDbIsInitiated()) {
            log.info("DB is ok");

        }
        log.info("Data base was not initiated...");
        log.info("Initiating Data Base...");
        Connection connection = getConnection();
        String[] queries = parseQuery("src/main/resources/initDb.sql");
        runQuerySet(connection, queries);
        if (!checkDbIsInitiated()){
            throw new RuntimeException("Initializated of the DB Failed after an attempt!");
        }
    }

    private static void runQuerySet(Connection connection, String[] queries) {
        log.info("Checking if DB is initiated...");
        for (String query: queries){
            try (Statement statement = connection.createStatement()){
               log.info("Running query: [{}]" + query);
               statement.executeUpdate(query);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public static void populateJDBC(){
        log.info("Checking if DB is initiated...");
        if (!checkDbIsInitiated()){
            throw new RuntimeException("The DB is not initialized!");
        }
        log.info("Population SQL Data Base with sandbox data...");
        Connection connection = getConnection();
        String [] queries = parseQuery("src/main/resources/sandBoxDataPopulation.sql");
        runQuerySet(connection, queries);
        log.info("Good! DB has been populated with fake data!");
    }

    private static String[] parseQuery(String path) {
        String[] query = null;
        try {
            query = String.join(System.lineSeparator(), Files.readAllLines(Paths.get(path))).split(QUERY_DELIMITER);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return query;
    }

    private static boolean checkDbIsInitiated() {
        boolean result = false;
        if (getConnection() != null) {
            result = true;
        }
        return result;
    }

    public static void populateInMem(){
        log.info("Population in memory Data Base with sandbox data...");
        User bilbo = new User(1L, LocalDate.now(), "Bilbo", LocalDate.of(1987,12,12), "I was first of owner ring");
        User legolas = new User(5L, LocalDate.now(), "Legolas", LocalDate.of(1987,12,12), "Nay, time does not tarry ever, but change and growth is not in all things and places alike");
        User frodo = new User(2L, LocalDate.now(), "Frodo", LocalDate.of(1987,12,12), "I`m second of owner ring");
        User sauron = new User(4L, LocalDate.now(), "Sauron", LocalDate.of(1987,12,12), "Lord");

        UserDao userDao = new UserDaoInMemImpl();
        userDao.save(bilbo);
        userDao.save(legolas);
        userDao.save(frodo);
        userDao.save(sauron);

        Tweet tweet = new Tweet(bilbo.getUserId(), null, "Hello everyones");
        Tweet tweetReply1 = new Tweet(legolas.getUserId(), 1L, "Hello a little guy");
        Tweet tweetReply2 = new Tweet(bilbo.getUserId(), 2L, "Ha-ha LoL");
        Tweet tweetReply3 = new Tweet(frodo.getUserId(), 3L, "Bilbo you are very old, you need a rest");
        Tweet tweetReply4 = new Tweet(sauron.getUserId(), 4L, "You cannot hide. I see you. There is no life in the void, only death");

         TweetDao tweetDao = new TweetDaoInMemImpl();
        tweetDao.saveTweet(tweet);
          tweetDao.saveTweet(tweetReply1);
         tweetDao.saveTweet(tweetReply2);
          tweetDao.saveTweet(tweetReply3);
          tweetDao.saveTweet(tweetReply4);


    }


}


