package model;

import controller.GameController;

import java.util.ArrayList;
import java.util.Collections;
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
    private List<Card[]> timeline;
    private int damage;

    private GameController gameController; // Reference to GameController

    public Player(String username, String password, String nickname, String email,
                  SecurityQuestion securityQuestion, String securityAnswer, List<Card> cardDeck,
                  int level, int hp, int xp, int coins) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.character = null;
        this.email = email;
        this.securityQuestion = securityQuestion;
        this.securityAnswer = securityAnswer;
        this.cardDeck = cardDeck;
        this.level = level;
        this.hp = hp;
        this.xp = xp;
        this.coins = coins;
        this.hand = new ArrayList<>();
        this.deck = new ArrayList<>();
        this.timeline = new ArrayList<>();
        this.damage = 0;
        drawInitialHand();
    }

    public Player(User user) {
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.nickname = user.getNickname();
        this.character = null;
        this.email = user.getEmail();
        this.securityQuestion = user.getSecurityQuestion();
        this.securityAnswer = user.getSecurityAnswer();
        this.cardDeck = new ArrayList<>();
        this.level = user.getLevel();
        this.hp = user.getHp();
        this.xp = user.getXp();
        this.coins = user.getCoins();
        this.hand = new ArrayList<>();
        this.deck = new ArrayList<>();
        this.timeline = new ArrayList<>();
        this.damage = 0;
        drawInitialHand();
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

    public String getUsername() {
        return username;
    }

    public GameController getGameController() {
        return gameController;
    }

    public void setGameController(GameController gameController) {
        this.gameController = gameController;
    }

    public String getPassword() {
        return password;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public void setFailedLoginAttempts(int i) {}

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

    public void drawInitialHand() {
        Collections.shuffle(cardDeck);
        for (int i = 0; i < 5; i++) {
            drawCard();
        }
    }

    public void drawCard() {
        if (!cardDeck.isEmpty()) {
            hand.add(cardDeck.remove(0));
        }
    }

    public void playCard(int cardIndex, int position) {
        Card card = hand.get(cardIndex);
        for (int i = 0; i < card.getDuration(); i++) {
            while (timeline.size() <= position + i) {
                timeline.add(new Card[1]);
            }
            timeline.get(position + i)[0] = card;
        }
        hand.remove(cardIndex);
        drawCard();
    }

    public boolean canPlaceCard(int cardIndex, int startPosition) {
        Card card = hand.get(cardIndex);
        for (int i = startPosition; i < startPosition + card.getDuration(); i++) {
            if (i >= timeline.size() || (timeline.get(i)[0] != null)) {
                return false;
            }
        }
        return true;
    }

    public List<Card[]> getTimeline() {
        return timeline;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

}