package pa.pro.pa_diplom.model;

import java.time.LocalDate;
import java.util.*;

public class Tweet {

    private Long tweetId;
    private Long userId;
    private Long referenceTweetId;
    private  LocalDate datePosted;
    private  String content;
    private  List<User> mentionedUsers;
    private List<User> likes;
    private List<Tweet> retweets;

    public Tweet(Long userId, Long referenceTweetId, String content) {

        this.userId = userId;
        this.referenceTweetId = referenceTweetId;
        this.datePosted = LocalDate.now();
        this.content = content;
        this.mentionedUsers = parseContentForMentions(content);
        this.likes = new ArrayList<>();
        this.retweets = new ArrayList<>();
    }

    public Tweet(Tweet other) {
        this.tweetId = other.tweetId;
        this.userId = other.userId;
        this.referenceTweetId = other.referenceTweetId;
        this.datePosted = other.datePosted;
        this.content = other.content;
        this.mentionedUsers = other.mentionedUsers;
        this.likes = other.likes;
        this.retweets = other.retweets;
    }

    public Tweet() {

    }

    public void setDatePosted(LocalDate datePosted) {
        this.datePosted = datePosted;
    }

    public void setContent(String content) {
        this.content = content;
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

    public Long getReferenceTweetId() {
        return referenceTweetId;
    }

    public void setReferenceTweetId(Long referenceTweetId) {
        this.referenceTweetId = referenceTweetId;
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
        return Objects.equals(tweetId, tweet.tweetId) && userId.equals(tweet.userId) && Objects.equals(referenceTweetId, tweet.referenceTweetId) && datePosted.equals(tweet.datePosted) && Objects.equals(content, tweet.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tweetId, userId, referenceTweetId, datePosted, content);
    }

    @Override
    public String toString() {
        return "Tweet{" +
                "tweetId=" + tweetId +
                ", userId=" + userId +
                ", referenceTweet=" + referenceTweetId +
                ", content='" + content + '\'' +
                '}';
    }


}
