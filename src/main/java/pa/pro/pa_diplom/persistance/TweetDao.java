package pa.pro.pa_diplom.persistance;

import pa.pro.pa_diplom.model.Tweet;
import java.util.List;
import java.util.Optional;

public interface TweetDao {

    Long saveTweet (Tweet tweet);

    Optional<Tweet> findTweetById(long tweetId);

    List<Tweet> getAll();


}
