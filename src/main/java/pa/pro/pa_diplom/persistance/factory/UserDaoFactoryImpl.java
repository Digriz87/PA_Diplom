package pa.pro.pa_diplom.persistance.factory;

import pa.pro.pa_diplom.persistance.UserDao;
import pa.pro.pa_diplom.persistance.UserDaoInMemImpl;
import pa.pro.pa_diplom.persistance.jdbc.UserDaoJdbcImpl;

public class UserDaoFactoryImpl implements UserDaoFactory{
    @Override
    public UserDao createJdbc() {
        return new UserDaoInMemImpl();
    }

    @Override
    public UserDao createInMem() {
        return new UserDaoJdbcImpl();
    }
}
