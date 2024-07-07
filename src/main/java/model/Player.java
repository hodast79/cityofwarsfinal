package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Player implements Serializable {
    private static final long serialVersionUID = 1L;

    private String username;
    private String password;
    private String nickname;
    private String email;
    private SecurityQuestion securityQuestion;
    private String securityAnswer;
    private List<Card> cardDeck;
    private int level;
    private int hp;
    private int xp;
    private int coins;

    public Player(String username, String password, String nickname, String email,
                  SecurityQuestion securityQuestion, String securityAnswer, List<Card> cardDeck,
                  int level, int hp, int xp, int coins) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.email = email;
        this.securityQuestion = securityQuestion;
        this.securityAnswer = securityAnswer;
        this.cardDeck = cardDeck;
        this.level = level;
        this.hp = hp;
        this.xp = xp;
        this.coins = coins;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public List<Card> getCards() {
        return cards;
    }

    // Other getters and setters


    public String getUsername() {
        return username;
    }

    public int getLevel() {
        return level;
    }

    public int getHp() {
        return hp;
    }

    public int getXp() {
        return xp;
    }

    public int getCoins() {
        return coins;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }
}
