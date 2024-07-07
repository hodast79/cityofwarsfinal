package model;

import utils.FileManager;

import java.util.ArrayList;
import java.util.List;


import java.util.List;

public class Admin {
    private List<Player> players;
    private List<Card> cards;

    public Admin() {
        this.players = FileManager.loadPlayers(); // assuming players are loaded from a file
        this.cards = FileManager.loadCards(); // assuming cards are loaded from a file
    }

    public List<Player> getPlayers() {
        return players;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void addCard(Card card) {
        cards.add(card);
        FileManager.saveCards(cards);
    }

    public void editCard(Card updatedCard) {
        for (int i = 0; i < cards.size(); i++) {
            if (cards.get(i).getName().equals(updatedCard.getName())) {
                cards.set(i, updatedCard);
                FileManager.saveCards(cards);
                return;
            }
        }
    }

    public void deleteCard(String cardName) {
        cards.removeIf(card -> card.getName().equals(cardName));
        FileManager.saveCards(cards);
    }

    public void viewAllCards() {
        for (int i = 0; i < cards.size(); i++) {
            System.out.println((i + 1) + ". " + cards.get(i).getName());
        }
    }
}



