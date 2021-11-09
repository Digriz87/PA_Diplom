package pa.pro.pa_diplom.persistance.jdbc;

import pa.pro.pa_diplom.model.Tweet;
import pa.pro.pa_diplom.model.User;
import pa.pro.pa_diplom.persistance.TweetDao;
import pa.pro.pa_diplom.persistance.TweetDaoInMemImpl;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TweetDaoJdbcImpl implements TweetDao {
    private static final String getLastId = "SELECT MAX(tweetId) FROM tweet";
    private static final String DELETE = "DELETE FROM tweet WHERE tweetId=?";
    private static final String FIND_ALL = "SELECT * FROM tweet ORDER BY tweetId";
    private static final String FIND_BY_ID = "SELECT * FROM tweet WHERE tweetId=?";
    private static final String INSERT = "INSERT INTO tweet(userId, referenceTweet, datePosted, content) VALUES(?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE tweet SET referenceTweet=?, datePosted=?, content=? WHERE tweetId=?";
    private static final String url = "jdbc:sqlite:twitter_db";
    static TweetDaoJdbcImpl tweetDaoJdbc = new TweetDaoJdbcImpl();
    @Override
    public Long saveTweet(Tweet tweet) {
        Connection connection = null;
        PreparedStatement statement1 = null;
        Statement statement2 = null;
        ResultSet rs = null;

        try {
            connection = getConnection();
            statement1 = connection.prepareStatement(INSERT);

            statement1.setLong(1, tweet.getUserId());
            statement1.setLong(2, tweet.getTweetId());
            statement1.setString(3, String.valueOf(tweet.getDatePosted()));
            statement1.setString(4, tweet.getContent());
            int result = statement1.executeUpdate();
            System.out.println(result);
            if (result == 1) {
                statement2 = connection.createStatement();
                rs = statement2.executeQuery(getLastId);
                if (rs.next()) {

                    return rs.getLong(1);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                rs.close();
                statement1.close();
                statement2.close();
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            return null;
        }
    }

    @Override
    public Optional<Tweet> findTweetById(long tweetId) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            stmt = conn.prepareStatement(FIND_BY_ID);
            stmt.setLong(1, tweetId);
            rs = stmt.executeQuery();

            if (rs.next()) {
                Tweet tweet = new Tweet();
                tweet.setTweetId(rs.getLong("tweetId"));
                tweet.setUserId(rs.getLong("userId"));
                tweet.setContent(rs.getString("content"));
                tweet.setDatePosted(LocalDate.parse(rs.getString("datePosted")));
                return Optional.of(tweet);
            } else {
                return null;
            }
        } catch (SQLException e) {

            throw new RuntimeException(e);
        } finally {
            oneMethodToCloseThemAll(rs,stmt,conn);
        }
    }

    @Override
    public List<Tweet> getAll() {
        Connection conn = null;
        PreparedStatement stmt = null;
        List<Tweet> list = new ArrayList<>();
        ResultSet rs = null;
        try {
            conn = getConnection();
            stmt = conn.prepareStatement(FIND_ALL);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Tweet tweet = new Tweet();
                tweet.setTweetId(rs.getLong("tweetId"));
                tweet.setUserId(rs.getLong("userId"));
                tweet.setContent(rs.getString("content"));
                tweet.setDatePosted(LocalDate.parse(rs.getString("datePosted")));

                list.add(tweet);
            }
        } catch (SQLException e) {

            throw new RuntimeException(e);
        } finally {
            oneMethodToCloseThemAll(rs,stmt,conn);
        }
        return list;
    }

    private Connection getConnection() {
        try {
            return DriverManager.getConnection(url);
        } catch (Exception e) {

            throw new RuntimeException(e);
        }
    }

    public void oneMethodToCloseThemAll(ResultSet resultSet, Statement statement, Connection connection) {
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
    public static void main(String[] args) {
       /* User bilbo = new User(1L,"Bilbo", new Timestamp(System.currentTimeMillis()), LocalDate.of(1987, 10,4), "I was first of owner ring");
        User legolas = new User(5L, "Legolas", new Timestamp(System.currentTimeMillis()), LocalDate.of(1980, 10, 4), "Nay, time does not tarry ever, but change and growth is not in all things and places alike");
        User frodo = new User(2L,"Frodo", new Timestamp(System.currentTimeMillis()), LocalDate.of(1987, 10,4), "I`m second of owner ring");
        User sauron = new User(4L,"Sauron", new Timestamp(System.currentTimeMillis()), LocalDate.of(1987, 10,4), "Lord");

        Tweet tweet = new Tweet(bilbo.getUserId(), null, "Hello everyones");
        Tweet tweetReply1 = new Tweet(legolas.getUserId(), tweet, "Hello a little guy");
        Tweet tweetReply2 = new Tweet(bilbo.getUserId(), tweetReply1, "Ha-ha LoL");
        Tweet tweetReply3 = new Tweet(frodo.getUserId(), tweetReply2, "Bilbo you are very old, you need a rest");
        Tweet tweetReply4 = new Tweet(sauron.getUserId(), tweetReply3, "You cannot hide. I see you. There is no life in the void, only death");

        TweetDao tweetDao = new TweetDaoInMemImpl();
        tweetDao.saveTweet(tweet);
        tweetDao.saveTweet(tweetReply1);
        tweetDao.saveTweet(tweetReply2);
        tweetDao.saveTweet(tweetReply3);
        tweetDao.saveTweet(tweetReply4);

         tweetDaoJdbc.saveTweet(tweet);
        tweetDaoJdbc.saveTweet(tweetReply1);
        tweetDaoJdbc.saveTweet(tweetReply2);
        tweetDaoJdbc.saveTweet(tweetReply3);
        tweetDaoJdbc.saveTweet(tweetReply4);*/

        System.out.println(tweetDaoJdbc.findTweetById(3));
        System.out.println(tweetDaoJdbc.getAll());
    }
}
