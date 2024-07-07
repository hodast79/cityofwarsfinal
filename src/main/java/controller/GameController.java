package controller;

import model.Card;
import model.Player;
import utils.FileManager;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class GameController {
    private Player player1;
    private Player player2;
    private List<Card> cardDeck;
    private final int DECK_SIZE = 21;
    private int unaccessibleBlock;
    private boolean isPlayer1Turn; // Added this variable to track whose turn it is


    Scanner scanner = new Scanner(System.in);

    public GameController(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.cardDeck = FileManager.loadCards(); // Load cards from file
    }


    public void startGame() {
        chooseCharacters();
        initializeDecks();
        showDecks();

        // Randomly determine which player starts
        Random random = new Random();
        isPlayer1Turn = random.nextBoolean();

        gameLoop();
    }


    private void chooseCharacters() {
        String[] characters = {"Warrior", "Mage", "Archer", "Assassin"};

        System.out.println(player1.getUsername() + ", choose your character:");
        for (int i = 0; i < characters.length; i++) {
            System.out.println((i + 1) + ". " + characters[i]);
        }
        int choice = scanner.nextInt();
        scanner.nextLine(); // consume newline
        player1.setCharacter(characters[choice - 1]);

        System.out.println(player2.getUsername() + ", choose your character:");
        for (int i = 0; i < characters.length; i++) {
            System.out.println((i + 1) + ". " + characters[i]);
        }
        choice = scanner.nextInt();
        scanner.nextLine(); // consume newline
        player2.setCharacter(characters[choice - 1]);
    }

    private void initializeDecks() {
        Random random = new Random();
        unaccessibleBlock = random.nextInt(DECK_SIZE); // Set the unaccessible block

        for (int i = 0; i < DECK_SIZE; i++) {
            if (i == unaccessibleBlock) {
                System.out.print("[X] ");
            } else {
                System.out.print("[ ] ");
            }
        }
        System.out.println();

        for (int i = 0; i < DECK_SIZE; i++) {
            if (i == unaccessibleBlock) {
                System.out.print("[X] ");
            } else {
                System.out.print("[ ] ");
            }
        }
        System.out.println();

        player1.initializeDeck(DECK_SIZE);
        player2.initializeDeck(DECK_SIZE);

        // Initialize players' hands with 5 random cards each
        drawInitialHand(player1);
        drawInitialHand(player2);
    }

    private void drawInitialHand(Player player) {
        Random random = new Random();
        List<Card> cardDeck = player.getCardDeck();
        for (int i = 0; i < 5; i++) {
            int randomIndex = random.nextInt(cardDeck.size());
            player.addCardToHand(cardDeck.get(randomIndex));
        }
    }
    private void showDecks() {
        System.out.println(player1.getUsername() + "'s deck:");
        player1.showDeck();
        showHand(player1);

        System.out.println(player2.getUsername() + "'s deck:");
        player2.showDeck();
        showHand(player2);
    }

    private void showHand(Player player) {
        System.out.println(player.getUsername() + "'s hand:");
        for (Card card : player.getHand()) {
            if (card == null) {
                System.out.print("[ ] ");
            } else {
                System.out.print("[" + card.getName() + "] ");
            }
        }
        System.out.println();
    }

    private void gameLoop() {
        boolean gameRunning = true;
        int rounds = 4; // Assuming each player has 4 turns

        while (gameRunning && rounds > 0) {
            if (isPlayer1Turn) {
                playerTurn(player1);
                showDecks();
                isPlayer1Turn = false;
            } else {
                playerTurn(player2);
                showDecks();
                isPlayer1Turn = true;
            }
            rounds--;
        }

        calculateDamage();
        displayWinner();
    }



    private void playerTurn(Player player) {
        System.out.println(player.getUsername() + "'s turn:");
        List<Card> hand = player.getHand();

        System.out.println("Choose a card to play:");
        for (int i = 0; i < hand.size(); i++) {
            System.out.println((i + 1) + ". " + hand.get(i).getName());
        }
        int cardChoice = scanner.nextInt() - 1;
        scanner.nextLine(); // consume newline

        Card chosenCard = hand.get(cardChoice);

        System.out.println("Choose a position to place the card (1 to " + DECK_SIZE + "):");
        int position = scanner.nextInt() - 1;
        scanner.nextLine(); // consume newline

        if (position == unaccessibleBlock) {
            System.out.println("Block is unaccessible. Turn skipped.");
            return;
        }

        if (!validatePosition(player, position, chosenCard.getDuration())) {
            System.out.println("Cannot place card here. Turn skipped.");
            return;
        }

        player.placeCardOnDeck(chosenCard, position);
        drawNewCard(player);
    }

    private boolean validatePosition(Player player, int position, int duration) {
        List<Card> deck = player.getDeck();
        for (int i = 0; i < duration; i++) {
            if (position + i >= DECK_SIZE || deck.get(position + i) != null || position + i == unaccessibleBlock) {
                return false;
            }
        }
        return true;
    }

    private void drawNewCard(Player player) {
        Random random = new Random();
        List<Card> cardDeck = player.getCardDeck();
        int randomIndex = random.nextInt(cardDeck.size());
        player.addCardToHand(cardDeck.get(randomIndex));
    }

    private void calculateDamage() {
        for (int i = 0; i < DECK_SIZE; i++) {
            Card card1 = player1.getDeck().get(i);
            Card card2 = player2.getDeck().get(i);

            if (card1 != null && (card2 == null || card1.getDefenseAttack() > card2.getDefenseAttack())) {
                int damage = card1.getPlayerDamage() / card1.getDuration();
                player2.setHp(player2.getHp() - damage);
            } else if (card2 != null && (card1 == null || card2.getDefenseAttack() > card1.getDefenseAttack())) {
                int damage = card2.getPlayerDamage() / card2.getDuration();
                player1.setHp(player1.getHp() - damage);
            }
        }
    }

    private void displayWinner() {
        if (player1.getHp() <= 0 && player2.getHp() <= 0) {
            System.out.println("It's a draw!");
        } else if (player1.getHp() <= 0) {
            System.out.println(player2.getUsername() + " wins!");
        } else if (player2.getHp() <= 0) {
            System.out.println(player1.getUsername() + " wins!");
        } else {
            System.out.println("Both players survived. It's a draw!");
        }
    }

    private void displayPlayerStatus(Player player) {
        System.out.println("Player: " + player.getUsername());
        System.out.println("HP: " + player.getHp());
        System.out.println("Level: " + player.getLevel());
        System.out.println("XP: " + player.getXp());
        System.out.println("Coins: " + player.getCoins());
    }

    private void displayCardOptions(Player player) {
        List<Card> cardDeck = player.getCardDeck();
        for (int i = 0; i < cardDeck.size(); i++) {
            Card card = cardDeck.get(i);
            System.out.println((i + 1) + ". " + card.getName() + " (defenseAttack: " + card.getDefenseAttack()+ ")");
        }
    }

    private void applyCardEffect(Card card, Player target) {
        System.out.println("Applying card: " + card.getName());
        target.setHp(target.getHp() - card.getPlayerDamage());
        System.out.println("Player " + target.getUsername() + " takes " + card.getPlayerDamage() + " damage.");
    }

    public void upgradeCard(Player player) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Select a card to upgrade:");
        displayCardOptions(player);

        int cardChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Card selectedCard = player.getCardDeck().get(cardChoice - 1);

        if (player.getCoins() >= selectedCard.getUpgradeCost()) {
            player.setCoins(player.getCoins() - selectedCard.getUpgradeCost());
            selectedCard.upgrade();
            System.out.println("Card upgraded successfully!");
        } else {
            System.out.println("Not enough coins to upgrade the card.");
        }
    }

    public void addCardToPlayerDeck(Player player) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Select a card to add to your deck:");
        displayCardOptionsFromDeck();

        int cardChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Card selectedCard = cardDeck.get(cardChoice - 1);
        player.addCardToDeck(selectedCard);
        System.out.println("Card added to your deck successfully!");
    }

    private void displayCardOptionsFromDeck() {
        for (int i = 0; i < cardDeck.size(); i++) {
            Card card = cardDeck.get(i);
            System.out.println((i + 1) + ". " + card.getName() + " (defenceAttack" + card.getDefenseAttack()+ ")");
        }
    }
}
