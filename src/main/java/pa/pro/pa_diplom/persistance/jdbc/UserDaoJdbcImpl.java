package pa.pro.pa_diplom.persistance.jdbc;

import pa.pro.pa_diplom.model.User;
import pa.pro.pa_diplom.persistance.UserDao;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDaoJdbcImpl implements UserDao {
    private static final String getLastId = "SELECT MAX(userId) FROM user";
    private static final String DELETE = "DELETE FROM user WHERE userId=?";
    private static final String FIND_ALL = "SELECT * FROM user ORDER BY userId";
    private static final String FIND_BY_ID = "SELECT * FROM user WHERE userId=?";
    private static final String INSERT = "INSERT INTO user(nickname, dateRegistered, dateOfBirth, about) VALUES(?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE user SET nickname=?, dateRegistered=?, dateOfBirth=?, about=? WHERE userId=?";
    private static final String url = "jdbc:sqlite:twitter_db";

    @Override
    public Long save(User user) {

        Connection connection = null;
        PreparedStatement statement1 = null;
        Statement statement2 = null;
        ResultSet rs = null;

        try {
            connection = getConnection();
            statement1 = connection.prepareStatement(INSERT);
            statement1.setString(1, user.getNickname());
            statement1.setString(2, String.valueOf(user.getDateRegistered()));
            statement1.setString(3, String.valueOf(user.getDateOfBirth()));
            statement1.setString(4, user.getAbout());
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
    public Optional<User> findUserById(long userId) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            stmt = conn.prepareStatement(FIND_BY_ID);
            stmt.setLong(1, userId);
            rs = stmt.executeQuery();

            if (rs.next()) {
                User user = new User();
                user.setUserId(rs.getLong("userId"));
                user.setNickname(rs.getString("nickname"));
                user.setAbout(rs.getString("about"));

                return Optional.of(user);
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
    public List<User> getAll() {
        Connection conn = null;
        PreparedStatement stmt = null;
        List<User> list = new ArrayList<>();
        ResultSet rs = null;
        try {
            conn = getConnection();
            stmt = conn.prepareStatement(FIND_ALL);
            rs = stmt.executeQuery();

            while (rs.next()) {
                User user = new User();
                user.setUserId(rs.getLong("userId"));
                user.setNickname(rs.getString("nickname"));
                user.setAbout(rs.getString("about"));
                user.setDateOfBirth(LocalDate.parse(rs.getString("dateOfBirth")));

                list.add(user);
            }
        } catch (SQLException e) {

            throw new RuntimeException(e);
        } finally {
            oneMethodToCloseThemAll(rs,stmt,conn);
        }
        return list;
    }

    @Override
    public void updateUser(User user) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = getConnection();
            stmt = conn.prepareStatement(UPDATE);
            stmt.setString(1, user.getNickname());
            stmt.setString(2, String.valueOf(user.getDateRegistered()));
            stmt.setString(3, String.valueOf(user.getDateOfBirth()));
            stmt.setString(4, user.getAbout());
            stmt.setLong(5, user.getUserId());
            stmt.executeUpdate();

        } catch (SQLException e) {

            throw new RuntimeException(e);
        } finally {
            try {
                stmt.close();
                conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    @Override
    public boolean deleteUserById(long userId) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = getConnection();
            stmt = conn.prepareStatement(DELETE);
            stmt.setLong(1, userId);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {

            throw new RuntimeException(e);
        } finally {
            try {
                stmt.close();
                conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }
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

        UserDaoJdbcImpl userDaoJdbc = new UserDaoJdbcImpl();
         User me = new User(null,"Slava", new Timestamp(System.currentTimeMillis()), LocalDate.of(1987, 10,4), "Student");

       // User newMe = new User(5L, "Legolas", new Timestamp(System.currentTimeMillis()), LocalDate.of(1980, 10, 4), "Nay, time does not tarry ever, but change and growth is not in all things and places alike");
        // userDaoJdbc.save(me);
       //userDaoJdbc.updateUser(newMe);
       // System.out.println(userDaoJdbc.findUserById(5));
        // System.out.println(userDaoJdbc.getAll());
        userDaoJdbc.deleteUserById(7);

    }
}
