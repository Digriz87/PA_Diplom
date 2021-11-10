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

    private static Logger log = Logger.getLogger(DbUtils.class.getName());

    public static Connection getConnection() {
        return getConnection(TWITTER_DB_URL);
    }

    private static Connection getConnection(String twitterDbUrl) {
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

    private static void initDb() {
        log.info("Checking if DB initiated...");
        if (checkDbIsInitiated()) {
            log.info("DB is ok");
            ;
        }
    }

    private static boolean checkDbIsInitiated() {
        boolean result = false;
        if (getConnection() != null) {
            result = true;
        }
        return result;
    }

}


