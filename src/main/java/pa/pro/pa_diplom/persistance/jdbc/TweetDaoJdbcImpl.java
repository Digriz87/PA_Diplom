package pa.pro.pa_diplom.persistance.jdbc;

import pa.pro.pa_diplom.model.Tweet;
import pa.pro.pa_diplom.persistance.TweetDao;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

public class TweetDaoJdbcImpl implements TweetDao {

    private static Logger log = Logger.getLogger(TweetDaoJdbcImpl.class.getName());

    @Override
    public Long saveTweet(Tweet tweet) {
        Connection connection = null;
        PreparedStatement statement1 = null;
        Statement statement2 = null;
        ResultSet rs = null;

        try {
            connection = DbUtils.getConnection();
            statement1 = connection.prepareStatement(DbUtils.INSERT_TWEET);
            statement1.setLong(1, tweet.getUserId());
            if (tweet.getReferenceTweetId() == null) {
                statement1.setNull(2, java.sql.Types.INTEGER);
            } else {
                statement1.setLong(2, tweet.getReferenceTweetId());
            }

            statement1.setString(3, String.valueOf(tweet.getDatePosted()));
            statement1.setString(4, tweet.getContent());
            int result = statement1.executeUpdate();
            log.info(String.valueOf(result));
            if (result == 1) {
                statement2 = connection.createStatement();
                rs = statement2.executeQuery(DbUtils.getLastId_TWEET);
                if (rs.next()) {
                    return rs.getLong(1);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DbUtils.closeResource(rs, statement1, connection);

        }
        return null;
    }

    @Override
    public Optional<Tweet> findTweetById(long tweetId) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = DbUtils.getConnection();
            stmt = conn.prepareStatement(DbUtils.FIND_BY_ID_TWEET);
            stmt.setLong(1, tweetId);
            rs = stmt.executeQuery();

            if (rs.next()) {
                Tweet tweet = new Tweet();
                tweet.setTweetId(rs.getLong("tweetId"));
                tweet.setUserId(rs.getLong("userId"));
                tweet.setContent(rs.getString("content"));
                tweet.setDatePosted(LocalDate.parse(rs.getString("datePosted")));
                tweet.setReferenceTweetId(rs.getLong("referenceTweet"));
                return Optional.of(tweet);
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {

            throw new RuntimeException(e);
        } finally {
            DbUtils.closeResource(rs, stmt, conn);
        }
    }

    @Override
    public List<Tweet> getAll() {
        Connection conn = null;
        PreparedStatement stmt = null;
        List<Tweet> list = new ArrayList<>();
        ResultSet rs = null;
        try {
            conn = DbUtils.getConnection();
            stmt = conn.prepareStatement(DbUtils.FIND_ALL_TWEET);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Tweet tweet = new Tweet();
                tweet.setTweetId(rs.getLong("tweetId"));
                tweet.setUserId(rs.getLong("userId"));
                tweet.setContent(rs.getString("content"));
                tweet.setDatePosted(LocalDate.parse(rs.getString("datePosted")));
                tweet.setReferenceTweetId(rs.getLong("referenceTweet"));
                list.add(tweet);
            }
        } catch (SQLException e) {

            throw new RuntimeException(e);
        } finally {
            DbUtils.closeResource(rs, stmt, conn);
        }
        return list;
    }

}
