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
        int blockedIndex = random.nextInt(DECK_SIZE);

        for (int i = 0; i < DECK_SIZE; i++) {
            if (i == blockedIndex) {
                System.out.print("[X] ");
            } else {
                System.out.print("[ ] ");
            }
        }
        System.out.println();

        for (int i = 0; i < DECK_SIZE; i++) {
            if (i == blockedIndex) {
                System.out.print("[X] ");
            } else {
                System.out.print("[ ] ");
            }
        }
        System.out.println();

        player1.initializeDeck(DECK_SIZE);
        player2.initializeDeck(DECK_SIZE);
    }

    private void showDecks() {
        System.out.println(player1.getUsername() + "'s deck:");
        for (Card card : player1.getDeck()) {
            if (card == null) {
                System.out.print("[ ] ");
            } else {
                System.out.print("[" + card.getName() + "] ");
            }
        }
        System.out.println();

        System.out.println(player2.getUsername() + "'s deck:");
        for (Card card : player2.getDeck()) {
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
        while (gameRunning) {
            playerTurn(player1);
            showDecks();
            playerTurn(player2);
            showDecks();

            // Add logic to check for game-ending conditions here
        }
    }



    private void playerTurn(Player player) {
        System.out.println(player.getUsername() + "'s turn:");
        List<Card> hand = player.getHand(); // Assuming player has a hand of cards

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

        player.placeCardOnDeck(chosenCard, position);
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
