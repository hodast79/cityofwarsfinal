package model;

import java.util.List;
import java.util.Random;

public class ReduceOpponentCard extends SpecialCard {
    public ReduceOpponentCard() {
        super("ReduceOpponentCard", "specialAbility", "description", "Assassin");
    }

    @Override
    public void useSpecialAbility(Player currentPlayer, Player opponent) {
        // Randomly select two cards from the opponent's hand
        List<Card> opponentHand = opponent.getHand();
        if (opponentHand.size() < 2) {
            System.out.println("Opponent does not have enough cards to reduce.");
            return;
        }
        Random random = new Random();
        int firstIndex = random.nextInt(opponentHand.size());
        int secondIndex = random.nextInt(opponentHand.size());

        // Ensure two different cards are selected
        while (secondIndex == firstIndex) {
            secondIndex = random.nextInt(opponentHand.size());
        }

        Card firstCard = opponentHand.get(firstIndex);
        Card secondCard = opponentHand.get(secondIndex);

        // Remove one card and reduce the power of the other card
        opponentHand.remove(firstCard);
        secondCard.setDefenseAttack(secondCard.getDefenseAttack() / 2);

        System.out.println("Opponent's card " + firstCard.getName() + " is destroyed.");
        System.out.println("Opponent's card " + secondCard.getName() + " has its power reduced.");
    }
}
