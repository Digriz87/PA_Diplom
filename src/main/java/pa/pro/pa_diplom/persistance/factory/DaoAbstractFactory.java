package pa.pro.pa_diplom.persistance.factory;

import pa.pro.pa_diplom.config.DaoType;
import pa.pro.pa_diplom.persistance.TweetDao;
import pa.pro.pa_diplom.persistance.UserDao;

public class DaoAbstractFactory {

    private DaoType type;

    UserDaoFactory userDaoFactory;
    TweetDaoFactory tweetDaoFactory;

    public DaoAbstractFactory(UserDaoFactory userDaoFactory, TweetDaoFactory tweetDaoFactory, DaoType type) {
        this.type = type;
        this.userDaoFactory =  userDaoFactory;
        this.tweetDaoFactory =  tweetDaoFactory;
    }

    public UserDao createUserDao() {
        return userDaoFactory.createUserDao(type);
    }

    public TweetDao createUTweetDao() {
        return tweetDaoFactory.createTweetDao(type);
    }

    public void setType(DaoType type) {
        this.type = type;
    }
}
