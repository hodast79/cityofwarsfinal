package model;

import java.util.ArrayList;
import java.util.List;

public class Admin {
    private List<Card> cards;

    public Admin() {
        cards = new ArrayList<>();
    }

    public List<Card> getCards() {
        return cards;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public void editCard(int cardIndex, Card newCard) {
        if (cardIndex >= 0 && cardIndex < cards.size()) {
            cards.set(cardIndex, newCard);
        }
    }

    public void deleteCard(int cardIndex) {
        if (cardIndex >= 0 && cardIndex < cards.size()) {
            cards.remove(cardIndex);
        }
    }

    public void viewAllCards() {
        for (int i = 0; i < cards.size(); i++) {
            System.out.println(i + ": " + cards.get(i).getName() + " - " + cards.get(i).getDescription());
        }
    }
}
