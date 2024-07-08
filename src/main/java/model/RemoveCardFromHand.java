package model;

import java.util.List;
import java.util.Random;

public class RemoveCardFromHand extends SpecialCard {
    public RemoveCardFromHand() {
        super("RemoveCardFromHand", "specialAbility", "description", "Archer");
    }

    @Override
    public void useSpecialAbility(Player currentPlayer, Player opponent) {
        List<Card> opponentHand = opponent.getHand();

        // Check if the opponent has enough cards to perform the action
        if (opponentHand.isEmpty()) {
            System.out.println("Opponent has no cards to remove.");
            return;
        }

        // Randomly select a card to remove from the opponent's hand
        Random random = new Random();
        int removedCardIndex = random.nextInt(opponentHand.size());
        Card removedCard = opponentHand.remove(removedCardIndex);

        // Add the removed card to the current player's hand
        currentPlayer.addCardToHand(removedCard);

        System.out.println("Removed card " + removedCard.getName() + " from opponent's hand and added it to current player's hand.");

        // Perform action: opponent will not draw a new card until 4 turns have passed


        //opponent.setTurnsUntilDraw(4);    **************** CHECK THIS IF THERE WAS ANY BUG!!! ***************




    }
}
