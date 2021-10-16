package pa.pro.pa_diplom.storage;

import pa.pro.pa_diplom.model.Feed;
import pa.pro.pa_diplom.model.Tweet;
import pa.pro.pa_diplom.model.User;

import java.util.HashMap;
import java.util.Map;

public class Storage {

    private static final Map<Long, User> userStorage = new HashMap<>();
    private static final Map<Long, Tweet> tweetStorage = new HashMap<>();
    public static long userIdSequence = 0;
    public static long tweetSequence = 0;


    private Storage() {

    }

    public static Map<Long, User> getUserStorage(){
        return userStorage;
    }
    public static Map<Long, Tweet> getTweetStorage(){
        return tweetStorage;
    }




}
