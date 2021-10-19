package pa.pro.pa_diplom.storage;


import pa.pro.pa_diplom.model.Tweet;
import pa.pro.pa_diplom.model.User;

import java.util.HashMap;
import java.util.Map;

public class Storage {

    public static long userIdSequence = 0;
    public static long tweetSequence = 0;

    private Storage() {

    }

    public static Map<Long, User> getUserStorage(){
        return userStorageHolder.userStorage;
    }
    public static Map<Long, Tweet> getTweetStorage(){
        return tweetStorageHolder.tweetStorage;
    }

    private static class userStorageHolder {
        private static final Map<Long, User> userStorage = new HashMap<>();

    }

    private static class tweetStorageHolder {
        private static final Map<Long, Tweet> tweetStorage = new HashMap<>();

    }









}
