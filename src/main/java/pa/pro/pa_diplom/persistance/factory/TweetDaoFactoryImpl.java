package pa.pro.pa_diplom.persistance.factory;

import pa.pro.pa_diplom.persistance.TweetDao;
import pa.pro.pa_diplom.persistance.TweetDaoInMemImpl;
import pa.pro.pa_diplom.persistance.UserDao;
import pa.pro.pa_diplom.persistance.UserDaoInMemImpl;
import pa.pro.pa_diplom.persistance.jdbc.TweetDaoJdbcImpl;
import pa.pro.pa_diplom.persistance.jdbc.UserDaoJdbcImpl;

public class TweetDaoFactoryImpl implements TweetDaoFactory{
    @Override
    public TweetDao createJdbc() {
        return new TweetDaoInMemImpl();
    }

    @Override
    public TweetDao createInMem() {
        return new TweetDaoJdbcImpl();
    }
}
