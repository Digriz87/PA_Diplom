package pa.pro.pa_diplom;

import pa.pro.pa_diplom.config.Configurator;
import pa.pro.pa_diplom.model.Tweet;
import pa.pro.pa_diplom.model.TweetFeed;
import pa.pro.pa_diplom.model.User;
import pa.pro.pa_diplom.model.UserFeed;
import pa.pro.pa_diplom.persistance.TweetDao;
import pa.pro.pa_diplom.persistance.TweetDaoInMemImpl;
import pa.pro.pa_diplom.persistance.jdbc.TweetDaoJdbcImpl;
import pa.pro.pa_diplom.persistance.jdbc.UserDaoJdbcImpl;

import java.time.LocalDate;


public class DemoRunner {
    public static void main(String[] args) {
        TweetDaoJdbcImpl tweetDaoJdbc = new TweetDaoJdbcImpl();
        UserDaoJdbcImpl userDaoJdbc = new UserDaoJdbcImpl();

        User bilbo = new User(1L, LocalDate.now(), "Bilbo", LocalDate.of(1987,12,12), "I was first of owner ring");
        User legolas = new User(5L, LocalDate.now(), "Legolas", LocalDate.of(1987,12,12), "Nay, time does not tarry ever, but change and growth is not in all things and places alike");
        User frodo = new User(2L, LocalDate.now(), "Frodo", LocalDate.of(1987,12,12), "I`m second of owner ring");
        User sauron = new User(4L, LocalDate.now(), "Sauron", LocalDate.of(1987,12,12), "Lord");

        Tweet tweet = new Tweet(bilbo.getUserId(), null, "Hello everyones");
        Tweet tweetReply1 = new Tweet(legolas.getUserId(), 1L, "Hello a little guy");
        Tweet tweetReply2 = new Tweet(bilbo.getUserId(), 2L, "Ha-ha LoL");
        Tweet tweetReply3 = new Tweet(frodo.getUserId(), 3L, "Bilbo you are very old, you need a rest");
        Tweet tweetReply4 = new Tweet(sauron.getUserId(), 4L, "You cannot hide. I see you. There is no life in the void, only death");

       // TweetDao tweetDao = new TweetDaoInMemImpl();
      //  tweetDao.saveTweet(tweet);
     //   tweetDao.saveTweet(tweetReply1);
      //  tweetDao.saveTweet(tweetReply2);
     //   tweetDao.saveTweet(tweetReply3);
     //   tweetDao.saveTweet(tweetReply4);

     //   tweetDaoJdbc.saveTweet(tweet);
     //   tweetDaoJdbc.saveTweet(tweetReply1);
     //   tweetDaoJdbc.saveTweet(tweetReply2);
     //   tweetDaoJdbc.saveTweet(tweetReply3);
     //   tweetDaoJdbc.saveTweet(tweetReply4);

       System.out.println(tweetDaoJdbc.findTweetById(3));
       // System.out.println(tweetDaoJdbc.getAll());
        System.out.println(userDaoJdbc.getAll());
        System.out.println(userDaoJdbc.findUserById(2));





    }

}
