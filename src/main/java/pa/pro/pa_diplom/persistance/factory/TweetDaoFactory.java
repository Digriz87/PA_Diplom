package pa.pro.pa_diplom.persistance.factory;

import pa.pro.pa_diplom.config.DaoType;
import pa.pro.pa_diplom.persistance.TweetDao;
import pa.pro.pa_diplom.persistance.UserDao;

public interface TweetDaoFactory {

    default TweetDao createTweetDao(DaoType type) {
        if (DaoType.IN_MEM.equals(type)) {
            return createInMem();
        } else if (DaoType.JDBC.equals(type)) {
            return createJdbc();
        }
        throw new IllegalArgumentException("Unknown dao type");
    }

    TweetDao createJdbc();

    TweetDao createInMem();

}
