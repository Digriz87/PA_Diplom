package pa.pro.pa_diplom.model;

import java.util.List;

public abstract class Feed {

    private final List<Tweet> tweets;

    public Feed(List<Tweet> tweets) {
        this.tweets = tweets;
    }

    public List<Tweet> getTweets() {
        return tweets;
    }

    @Override
    public String toString() {
        return "Feed{" +
                "tweets=" + tweets +
                '}';
    }
}
