package pa.pro.pa_diplom.persistance.jdbc;

import pa.pro.pa_diplom.model.User;
import pa.pro.pa_diplom.persistance.UserDao;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.logging.Logger;

public class UserDaoJdbcImpl implements UserDao {

    DbUtils dbUtils = new DbUtils();
    private static Logger log = Logger.getLogger(UserDaoJdbcImpl.class.getName());


    @Override
    public Long save(User user) {

        Connection connection = null;
        PreparedStatement statement1 = null;
        Statement statement2 = null;
        ResultSet rs = null;

        try {
            connection = dbUtils.getConnection();
            statement1 = connection.prepareStatement(dbUtils.INSERT_USER);
            statement1.setString(1, user.getNickname());
            statement1.setDate(2, Date.valueOf(user.getDateRegistered()));
            statement1.setString(3, user.getDateOfBirth());
            statement1.setString(4, user.getAbout());
            int result = statement1.executeUpdate();
            log.info(String.valueOf(result));
            if (result == 1) {
                statement2 = connection.createStatement();
                rs = statement2.executeQuery(dbUtils.getLastId_USER);
                if (rs.next()) {
                    return rs.getLong(1);
                }

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            dbUtils.closeRsStSt2Cn(rs,statement1,statement2,connection);

        }return null;
    }

    @Override
    public Optional<User> findUserById(long userId) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        User user = null;
        try {
            conn = dbUtils.getConnection();
            stmt = conn.prepareStatement(dbUtils.FIND_BY_ID_USER);
            stmt.setLong(1, userId);
            rs = stmt.executeQuery();

            if (rs.next()) {
                user = new User();
                user.setUserId(rs.getLong("userId"));
                user.setNickname(rs.getString("nickname"));
                user.setAbout(rs.getString("about"));
                user.setDateOfBirth(rs.getString("dateOfBirth"));
                user.setDateRegistered(LocalDate.ofEpochDay(rs.getLong("dateRegistered")));
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            dbUtils.closeRsStCn(rs, stmt, conn);
        }
        return Optional.ofNullable(user);
    }

    @Override
    public List<User> getAll() {
        Connection conn = null;
        PreparedStatement stmt = null;
        List<User> list = new ArrayList<>();
        ResultSet rs = null;


        try {
            conn = dbUtils.getConnection();
            stmt = conn.prepareStatement(dbUtils.FIND_ALL_USER);
            rs = stmt.executeQuery();

            while (rs.next()) {
                User user = new User();
                user.setUserId(rs.getLong("userId"));
                user.setNickname(rs.getString("nickname"));
                user.setAbout(rs.getString("about"));
                user.setDateOfBirth((rs.getString("dateOfBirth")));
                user.setDateRegistered(LocalDate.ofEpochDay(rs.getLong("dateRegistered")));
                list.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            dbUtils.closeRsStCn(rs,stmt,conn);
        }
        return list;
    }

    @Override
    public void updateUser(User user) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = dbUtils.getConnection();
            stmt = conn.prepareStatement(dbUtils.UPDATE_USER);
            stmt.setString(1, user.getNickname());
            stmt.setDate(2, Date.valueOf(user.getDateRegistered()));
            stmt.setString(3, user.getDateOfBirth());
            stmt.setString(4, user.getAbout());
            stmt.setLong(5, user.getUserId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            dbUtils.closeRsStCn(null,stmt,conn);
        }
    }

    @Override
    public boolean deleteUserById(long userId) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = dbUtils.getConnection();
            stmt = conn.prepareStatement(dbUtils.DELETE_USER);
            stmt.setLong(1, userId);
            stmt.executeUpdate();

            return  stmt.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            dbUtils.closeRsStCn(null,stmt,conn);
        }
        return true;
    }

}
