package pa.pro.pa_diplom.service;

import pa.pro.pa_diplom.model.User;
import pa.pro.pa_diplom.persistance.UserDao;

import java.util.Optional;

public class UserServiceImpl implements UserService{


    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public Long createUser(User user) {
        return userDao.save(user);
    }

    @Override
    public void deleteUser(long id) {
        userDao.deleteUserById(id);

    }

    @Override
    public void updateUser(User user) {
         userDao.updateUser(user);
    }

    @Override
    public Optional<User> findUserById(long id) {
        return userDao.findUserById(id);
    }
}
