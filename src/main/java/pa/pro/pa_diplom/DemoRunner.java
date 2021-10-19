package pa.pro.pa_diplom;

import pa.pro.pa_diplom.model.Tweet;
import pa.pro.pa_diplom.model.TweetFeed;
import pa.pro.pa_diplom.model.User;
import pa.pro.pa_diplom.model.UserFeed;
import pa.pro.pa_diplom.persistance.TweetDao;
import pa.pro.pa_diplom.persistance.TweetDaoInMemImpl;
import pa.pro.pa_diplom.persistance.UserDao;
import pa.pro.pa_diplom.persistance.UserDaoInMemImpl;
import pa.pro.pa_diplom.service.UserService;
import pa.pro.pa_diplom.service.UserServiceImpl;
import pa.pro.pa_diplom.storage.Storage;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Map;
import java.util.Optional;

public class DemoRunner {
    public static void main(String[] args) {

        User me = new User("Slava", LocalDate.of(1987, 10,4), "Student");
        User him = new User("Kostya", LocalDate.of(1990, 2,10), "Teacher");

        Tweet tweet = new Tweet(me.getUserId(), null, "Hello everyones");
        Tweet tweetReply1 = new Tweet(him.getUserId(), tweet, "Hello Slavik");
        Tweet tweetReply2 = new Tweet(me.getUserId(), tweetReply1, "You too");

        UserFeed myFeed = new UserFeed(Arrays.asList(tweet, tweetReply2), me.getUserId(), true);
        TweetFeed myTweetFeed = new TweetFeed(Arrays.asList(tweetReply1,tweetReply2), tweet.getTweetId());


        UserDao UserDao = new UserDaoInMemImpl();
        UserService userService = new UserServiceImpl(UserDao);
        TweetDao TweetDao = new TweetDaoInMemImpl();
        TweetDao.saveTweet(tweet);
        TweetDao.saveTweet(tweetReply1);
        userService.createUser(me);
        userService.createUser(him);

        me.setAbout("Hello LoL");

        Optional<User> oldMe = userService.findUserById(me.getUserId());
       // Optional<Tweet> newTweets = TweetDao.findTweetById(tweet.getTweetId());
        System.out.println(oldMe);
        System.out.println(me);

        System.out.println(tweet.getContent());
        System.out.println(tweetReply1.getContent());


        Map<Long, Tweet> storage = Storage.getTweetStorage();


    }

}
