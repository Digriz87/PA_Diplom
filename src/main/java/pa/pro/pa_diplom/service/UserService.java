package pa.pro.pa_diplom.service;

import pa.pro.pa_diplom.model.User;

import java.util.Optional;

public interface UserService {

    Long createUser(User user);
    void deleteUser(long id);
    void updateUser(User user);
    Optional<User> findUserById(long id);


}
