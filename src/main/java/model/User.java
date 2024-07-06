package model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import utils.FileManager;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    private String username;
    private String password;
    private String nickname;
    private String email;
    private SecurityQuestion securityQuestion;
    private String securityAnswer;
    private int failedLoginAttempts;

    // New attributes for the user
    private List<Card> cardDeck;
    private int level;
    private int hp;
    private int xp;
    private int coins;

    private static HashMap<String, User> users = FileManager.loadUsers();

    // Default constructor for Jackson
    public User() {
    }

    // Parameterized constructor
    @JsonCreator
    public User(
            @JsonProperty("username") String username,
            @JsonProperty("password") String password,
            @JsonProperty("nickname") String nickname,
            @JsonProperty("email") String email,
            @JsonProperty("securityQuestion") SecurityQuestion securityQuestion,
            @JsonProperty("securityAnswer") String securityAnswer,
            @JsonProperty("cardDeck") List<Card> cardDeck,
            @JsonProperty("level") int level,
            @JsonProperty("hp") int hp,
            @JsonProperty("xp") int xp,
            @JsonProperty("coins") int coins
    ) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.email = email;
        this.securityQuestion = securityQuestion;
        this.securityAnswer = securityAnswer;
        this.failedLoginAttempts = 0;
        this.cardDeck = cardDeck;
        this.level = level;
        this.hp = hp;
        this.xp = xp;
        this.coins = coins;
    }

    // Getters and Setters
    public String getUsername() {
        return username;
    }
    public static void updateUser(User user) {
        users.put(user.getUsername(), user);
        FileManager.saveUsers(users);
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public SecurityQuestion getSecurityQuestion() {
        return securityQuestion;
    }

    public void setSecurityQuestion(SecurityQuestion securityQuestion) {
        this.securityQuestion = securityQuestion;
    }

    public String getSecurityAnswer() {
        return securityAnswer;
    }

    public void setSecurityAnswer(String securityAnswer) {
        this.securityAnswer = securityAnswer;
    }

    public int getFailedLoginAttempts() {
        return failedLoginAttempts;
    }

    public void setFailedLoginAttempts(int failedLoginAttempts) {
        this.failedLoginAttempts = failedLoginAttempts;
    }

    public List<Card> getCardDeck() {
        return cardDeck;
    }

    public void setCardDeck(List<Card> cardDeck) {
        this.cardDeck = cardDeck;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public static void addUser(User user) {
        users.put(user.getUsername(), user);
        FileManager.saveUsers(users);
    }

    public static User getUser(String username) {
        return users.get(username);
    }

    public static boolean usernameExists(String username) {
        return users.containsKey(username);
    }
}
