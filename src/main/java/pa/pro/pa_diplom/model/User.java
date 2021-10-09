package pa.pro.pa_diplom.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class User {

    private static long userIdSequence = 0;

    private Long userId;
    private String nickname;
    private final LocalDate dateRegistered;
    private LocalDate dateOfBirth;
    private String about;
    private List<User> followers;
    private List<User> following;

    public User(String nickname, LocalDate dateOfBirth, String about) {
        this.userId = ++userIdSequence;
        this.nickname = nickname;
        this.dateRegistered = LocalDate.now();
        this.dateOfBirth = dateOfBirth;
        this.about = about;
        this.followers = new ArrayList<>();
        this.following = new ArrayList<>();
    }

    public User(String nickname) {
        this.userId = ++userIdSequence;
        this.nickname = nickname;
        this.dateRegistered = LocalDate.now();
        this.followers = new ArrayList<>();
        this.following = new ArrayList<>();
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public LocalDate getDateRegistered() {
        return dateRegistered;
    }


    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public List<User> getFollowers() {
        return followers;
    }

    public void setFollowers(List<User> followers) {
        this.followers = followers;
    }

    public List<User> getFollowing() {
        return following;
    }

    public void setFollowing(List<User> following) {
        this.following = following;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userId, user.userId) && Objects.equals(nickname, user.nickname) && dateRegistered.equals(user.dateRegistered) && Objects.equals(dateOfBirth, user.dateOfBirth);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, nickname, dateRegistered, dateOfBirth);
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", nickname='" + nickname + '\'' +
                ", dateRegistered=" + dateRegistered +
                ", about='" + about + '\'' +
                '}';
    }
}
