package model;

import utils.FileManager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Admin implements Serializable {
    private static final long serialVersionUID = 1L;

    private static List<Card> cards = FileManager.loadCards();
    private static List<Player> players = FileManager.loadPlayers();

    // Add a card to the game
    public void addCard(Card card) {
        if (!cardExists(card.getName())) {
            cards.add(card);
            FileManager.saveCards(cards);
            System.out.println("Card added successfully!");
        } else {
            System.out.println("Card with this name already exists.");
        }
    }

    // Edit an existing card
    public void editCard(String cardName, Card updatedCard) {
        for (int i = 0; i < cards.size(); i++) {
            if (cards.get(i).getName().equals(cardName)) {
                cards.set(i, updatedCard);
                FileManager.saveCards(cards);
                System.out.println("Card updated successfully!");
                return;
            }
        }
        System.out.println("Card not found.");
    }

    // Delete a card from the game
    public void deleteCard(String cardName) {
        cards.removeIf(card -> card.getName().equals(cardName));
        FileManager.saveCards(cards);
        System.out.println("Card deleted successfully!");
    }

    // View all players
    public List<Player> viewAllPlayers() {
        return new ArrayList<>(players);
    }

    // Add a player to the game (For demonstration purpose, normally player should be added through registration)
    public static void addPlayer(Player player) {
        players.add(player);
        FileManager.savePlayers(players);
    }

    // Check if a card with the given name already exists
    private boolean cardExists(String cardName) {
        for (Card card : cards) {
            if (card.getName().equals(cardName)) {
                return true;
            }
        }
        return false;
    }

    // Getters and Setters for cards and players lists (if needed)
    public static List<Card> getCards() {
        return cards;
    }

    public static List<Player> getPlayers() {
        return players;
    }
}
