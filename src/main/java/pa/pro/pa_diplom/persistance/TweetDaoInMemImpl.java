package pa.pro.pa_diplom.persistance;

import pa.pro.pa_diplom.model.Tweet;
import pa.pro.pa_diplom.storage.Storage;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TweetDaoInMemImpl implements TweetDao {


    @Override
    public Long saveTweet(Tweet tweet) {
        long newTweetId = ++Storage.tweetSequence;
        tweet.setTweetId(newTweetId);
        Storage.getTweetStorage().put(tweet.getTweetId(), createTweetState(tweet));
        return newTweetId;
    }

    @Override
    public Optional<Tweet> findTweetById(long tweetId) {

        final Tweet persistedTweet = Storage.getTweetStorage().get(tweetId);
        if (persistedTweet != null){
            Tweet resultTweet = createTweetState(persistedTweet);
            return Optional.of(resultTweet);
        }

        return Optional.empty();
    }

    @Override
    public List<Tweet> getAll() {
        List<Tweet> tweetList = new ArrayList<>();
        for (Tweet tweet: Storage.getTweetStorage().values()){
            tweetList.add(createTweetState(tweet));
        }
        return tweetList;
    }


    private Tweet createTweetState (Tweet tweetOriginal){
        return new Tweet(tweetOriginal);

    }
}
