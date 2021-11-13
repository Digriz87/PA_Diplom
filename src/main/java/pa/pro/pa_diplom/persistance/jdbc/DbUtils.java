package pa.pro.pa_diplom.persistance.jdbc;

import java.sql.*;
import java.util.logging.Logger;

public class DbUtils {

    private static final String TWITTER_DB_URL = "jdbc:sqlite:twitter_db";
    public static final String USER = "user";
    public static final String TWEET = "tweet";
    public static final String LIKES = "likes";
    public static final String FOLLOWERS = "followers";
    public static final String QUERY_DELIMITER = "--NEXT--";
    public  final String getLastId_USER = "SELECT MAX(userId) FROM user";
    public  final String DELETE_USER = "DELETE FROM user WHERE userId=?";
    public  final String FIND_ALL_USER = "SELECT * FROM user ORDER BY userId";
    public  final String FIND_BY_ID_USER = "SELECT * FROM user WHERE userId=?";
    public  final String INSERT_USER = "INSERT INTO user(nickname, dateRegistered, dateOfBirth, about) VALUES(?, ?, ?, ?)";
    public  final String UPDATE_USER = "UPDATE user SET nickname=?, dateRegistered=?, dateOfBirth=?, about=? WHERE userId=?";
    public  final String getLastId_TWEET = "SELECT MAX(tweetId) FROM tweet";
    public  final String DELETE_TWEET = "DELETE FROM tweet WHERE tweetId=?";
    public  final String FIND_ALL_TWEET = "SELECT * FROM tweet ORDER BY tweetId";
    public  final String FIND_BY_ID_TWEET = "SELECT * FROM tweet WHERE tweetId=?";
    public  final String INSERT_TWEET = "INSERT INTO tweet(userId, referenceTweet, datePosted, content) VALUES(?, ?, ?, ?)";
    public  final String UPDATE_TWEET = "UPDATE tweet SET referenceTweet=?, datePosted=?, content=? WHERE tweetId=?";

    public void closeRsStCn(ResultSet resultSet, Statement statement, Connection connection) {
        if (resultSet != null) {
            try {
                if (!resultSet.isClosed()) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (statement != null) {
            try {
                if (!statement.isClosed()) {
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (connection != null) {
            try {
                if (!connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void closeRsStSt2Cn(ResultSet resultSet, Statement statement, Statement statement2, Connection connection) {
        if (resultSet != null) {
            try {
                if (!resultSet.isClosed()) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (statement != null) {
            try {
                if (!statement.isClosed()) {
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (statement2 != null) {
            try {
                if (!statement.isClosed()) {
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                if (!connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    private static Logger log = Logger.getLogger(DbUtils.class.getName());

    public  Connection getConnection() {
        return getConnection(TWITTER_DB_URL);
    }

    private  Connection getConnection(String twitterDbUrl) {
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

    public void main(String[] args) {
        initDb();

    }

    private  void initDb() {
        log.info("Checking if DB initiated...");
        if (checkDbIsInitiated()) {
            log.info("DB is ok");
            ;
        }
    }

    private boolean checkDbIsInitiated() {
        boolean result = false;
        if (getConnection() != null) {
            result = true;
        }
        return result;
    }

}


