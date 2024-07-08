package model;

import java.util.List;
import java.util.Random;

public class WeakeningCard extends SpecialCard {
    public WeakeningCard() {
        super("WeakeningCard", "specialAbility", "description", "Mage");
    }

    @Override
    public void useSpecialAbility(Player currentPlayer, Player opponent) {
        List<Card> opponentDeck = opponent.getDeck();
        Random random = new Random();

        // Randomly select two cards from the opponent's deck
        int firstCardIndex = random.nextInt(opponentDeck.size());
        int secondCardIndex = random.nextInt(opponentDeck.size());

        Card firstCard = opponentDeck.get(firstCardIndex);
        Card secondCard = opponentDeck.get(secondCardIndex);

        // Reduce the power of the selected cards
        if (firstCard != null) {
            firstCard.setDefenseAttack(Math.max(0, firstCard.getDefenseAttack() - 10)); // Example reduction, adjust as needed
            System.out.println("Weakened " + firstCard.getName() + " to " + firstCard.getDefenseAttack());
        }

        if (secondCard != null) {
            secondCard.setDefenseAttack(Math.max(0, secondCard.getDefenseAttack() - 10)); // Example reduction, adjust as needed
            System.out.println("Weakened " + secondCard.getName() + " to " + secondCard.getDefenseAttack());
        }
    }
}
