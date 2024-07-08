package model;

import java.util.Random;

public class PowerUpCard extends SpecialCard {

    public PowerUpCard() {
        super("PowerUpCard", "specialAbility", "description", "Mage");
    }


    @Override
    public void useSpecialAbility(Player currentPlayer, Player opponent) {
        // Check if the current player has cards to power up
        if (currentPlayer.getHand().isEmpty()) {
            System.out.println(currentPlayer.getUsername() + " has no cards to power up.");
            return;
        }

        // Randomly select one of the cards from the player's hand
        Random random = new Random();
        int cardIndex = random.nextInt(currentPlayer.getHand().size());
        Card selectedCard = currentPlayer.getHand().get(cardIndex);

        // Increase the power of the selected card
        selectedCard.setDefenseAttack((int) (selectedCard.getDefenseAttack() * 1.5)); // Example: Increase power by 50%
        System.out.println(currentPlayer.getUsername() + " used " + getName() + " on " + selectedCard.getName() + " increasing its power to " + selectedCard.getDefenseAttack() + ".");
    }

}
