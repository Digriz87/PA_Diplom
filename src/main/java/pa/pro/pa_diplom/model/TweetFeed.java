package pa.pro.pa_diplom.model;

import java.util.List;

public class TweetFeed extends Feed{

   private final Long tweetId;

    public TweetFeed(List<Tweet> tweets, Long tweetId) {
        super(tweets);
        this.tweetId = tweetId;
    }

    public Long getTweetId() {
        return tweetId;
    }

    @Override
    public String toString() {
        return "TweetFeed{" +
                "tweetId=" + tweetId +
                '}';
    }
}
