package pa.pro.pa_diplom.model;


import java.util.List;

public class UserFeed extends Feed{

    private final Long userId;
    private final boolean homeFeed;

    public UserFeed(List<Tweet> tweets, Long userId, boolean homeFeed) {
        super(tweets);
        this.userId = userId;
        this.homeFeed = homeFeed;
    }

    public Long getUserId() {
        return userId;
    }


    public boolean isHomeFeed() {
        return homeFeed;
    }

    @Override
    public String toString() {
        return "UserFeed{" +
                "userId=" + userId +
                ", homeFeed=" + homeFeed +
                '}';
    }
}
