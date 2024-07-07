package model;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private String username;
    private String password;
    private String nickname;
    private String email;
    private SecurityQuestion securityQuestion;
    private String securityAnswer;
    private String character;

    private List<Card> cardDeck;
    private int level;
    private int hp;
    private int xp;
    private int coins;

    private List<Card> hand;
    private List<Card> deck;

    public Player(String username, String password, String nickname, String email,
                  SecurityQuestion securityQuestion, String securityAnswer, List<Card> cardDeck,
                  int level, int hp, int xp, int coins) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.character = null; // Default to null until set by player
        this.deck = new ArrayList<>();
        this.email = email;
        this.securityQuestion = securityQuestion;
        this.securityAnswer = securityAnswer;
        this.cardDeck = cardDeck;
        this.level = level;
        this.hp = hp;
        this.xp = xp;
        this.coins = coins;
        this.hand = new ArrayList<>();
    }

    public Player (User user){
        this.username = user.username;
        this.password = user.password;
        this.nickname = user.nickname;
        this.character = null; // Default to null until set by player
        this.deck = new ArrayList<>();
        this.email = user.email;
        this.securityQuestion = user.securityQuestion;
        this.securityAnswer = user.securityAnswer;
        this.cardDeck = null;
        this.level = user.getLevel();
        this.hp = user.getHp();
        this.xp = user.getXp();
        this.coins = user.getCoins();
        this.hand = new ArrayList<>();

    }

    public List<Card> getCardDeck() {
        return cardDeck;
    }

    public void setCardDeck(List<Card> cardDeck) {
        this.cardDeck = cardDeck;
    }

    public void setHand(List<Card> hand) {
        this.hand = hand;
    }

    public void setDeck(List<Card> deck) {
        this.deck = deck;
    }

    // Getters and Setters
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

//    public List<Card> getCardDeck() {
//        return cardDeck;
//    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public void setFailedLoginAttempts(int i) {
    }

    public int getFailedLoginAttempts() {
        return 0;
    }

    // Method to display player info
    public void displayInfo() {
        System.out.println("Username: " + username);
        System.out.println("Nickname: " + nickname);
        System.out.println("Email: " + email);
        System.out.println("Level: " + level);
        System.out.println("HP: " + hp);
        System.out.println("XP: " + xp);
        System.out.println("Coins: " + coins);
    }



    // Method to change password
    public boolean changePassword(String oldPassword, String newPassword) {
        if (this.password.equals(oldPassword)) {
            this.password = newPassword;
            return true;
        }
        return false;
    }

    public void setUsername(String username) {
        this.username = username;
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

//    public void setCardDeck(List<Card> cardDeck) {
//        this.cardDeck = cardDeck;
//    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
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

    public void addCardToDeck(Card card) {
        this.cardDeck.add(card);
    }
    public void setCharacter(String character) {
        this.character = character;
    }

    public String getCharacter() {
        return character;
    }

    public List<Card> getDeck() {
        return deck;
    }
    public void initializeDeck(int deckSize) {
        for (int i = 0; i < deckSize; i++) {
            deck.add(null); // Initialize deck with null values
        }
    }

    public void placeCardOnDeck(Card card, int position) {
        for (int i = 0; i < card.getDuration(); i++) {
            if (position + i < deck.size()) {
                deck.set(position + i, card);
            } else {
                System.out.println("Card placement exceeds deck size.");
                break;
            }
        }
    }
    public List<Card> getHand() {
        return hand;
    }

    public void addCardToHand(Card card) {
        hand.add(card);
    }

    public void showDeck() {
        for (Card card : deck) {
            if (card == null) {
                System.out.print("[ ] ");
            } else {
                System.out.print("[" + card.getName() + "] ");
            }
        }
        System.out.println();
    }

// Other methods for changing username, email, etc.
}
