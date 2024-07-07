package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Player implements Serializable {
    private static final long serialVersionUID = 1L;

    private String username;
    private int level;
    private int hp;
    private int xp;
    private int coins;
    private List<Card> cards;

    public Player(String username) {
        this.username = username;
        this.level = 1;
        this.hp = 100;
        this.xp = 0;
        this.coins = 0;
        this.cards = new ArrayList<>();
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

    public void setHp(int hp) {
        this.hp = hp;
    }
}
