package model;

import utils.FileManager;

import java.util.List;

public class Admin  {
    private List<Card> cards;
    private List<Player> players;

    public Admin() {
        this.cards = FileManager.loadCards();
        this.players = FileManager.loadPlayers();
    }

    // Methods to add, edit, delete cards
    public void addCard(Card card) {
        cards.add(card);
        FileManager.saveCards(cards);
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public void editCard(String cardName, Card updatedCard) {
        for (int i = 0; i < cards.size(); i++) {
            if (cards.get(i).getName().equals(cardName)) {
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

    public List<Card> viewAllCards() {
        return FileManager.loadCards();
    }

    public void viewAllPlayers() {
        for (Player player : players) {
            System.out.println(player.getUsername());
        }
    }

    public List<Player> getPlayers() {
        return players;
    }



}
