package pa.pro.pa_diplom.model;

import java.time.LocalDate;
import java.util.*;

public class Tweet {

    private static long tweetSequence = 0;

    private Long tweetId;
    private Long userId;
    private Tweet referenceTweet;
    private final LocalDate datePosted;
    private final String content;
    private final List<User> mentionedUsers;
    private List<User> likes;
    private List<Tweet> retweets;

    public Tweet(Long userId, Tweet referenceTweet, String content) {
        this.tweetId = ++tweetSequence;
        this.userId = userId;
        this.referenceTweet = referenceTweet;
        this.datePosted = LocalDate.now();
        this.content = content;
        this.mentionedUsers = parseContentForMentions(content);
        this.likes = new ArrayList<>();
        this.retweets = new ArrayList<>();
    }

    private List<User> parseContentForMentions(String content) {
        return Collections.emptyList();
    }

    public Long getTweetId() {
        return tweetId;
    }

    public void setTweetId(Long tweetId) {
        this.tweetId = tweetId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Tweet getReferenceTweet() {
        return referenceTweet;
    }

    public void setReferenceTweet(Tweet referenceTweet) {
        this.referenceTweet = referenceTweet;
    }

    public LocalDate getDatePosted() {
        return datePosted;
    }

    public String getContent() {
        return content;
    }


    public List<User> getMentionedUsers() {
        return mentionedUsers;
    }

    public List<User> getLikes() {
        return likes;
    }

    public void setLikes(List<User> likes) {
        this.likes = likes;
    }

    public List<Tweet> getRetweets() {
        return retweets;
    }

    public void setRetweets(List<Tweet> retweets) {
        this.retweets = retweets;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tweet tweet = (Tweet) o;
        return Objects.equals(tweetId, tweet.tweetId) && userId.equals(tweet.userId) && Objects.equals(referenceTweet, tweet.referenceTweet) && datePosted.equals(tweet.datePosted) && Objects.equals(content, tweet.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tweetId, userId, referenceTweet, datePosted, content);
    }

    @Override
    public String toString() {
        return "Tweet{" +
                "tweetId=" + tweetId +
                ", userId=" + userId +
                ", referenceTweet=" + referenceTweet +
                ", content='" + content + '\'' +
                '}';
    }
}
