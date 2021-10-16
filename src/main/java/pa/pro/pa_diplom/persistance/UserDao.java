package pa.pro.pa_diplom.persistance;

import pa.pro.pa_diplom.model.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {

    Long save (User user);

    Optional<User> findUserById(long userId);

    List<User> getAll();


    /**
     * If @param user is not persisted in the DB, then throw a custom RuntimeException ToDo
     * @param user
     */

    void updateUser (User user);

    boolean deleteUserById (long userId);






}
