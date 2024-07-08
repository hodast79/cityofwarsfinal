package model;

public class ChangeLocationCard extends SpecialCard {
    public ChangeLocationCard() {
        super("ChangeLocationCard", "specialAbility", "description", "Mage");
    }


    @Override
    public void useSpecialAbility(Player currentPlayer, Player opponent) {
        // Implementation of the special ability
        System.out.println("Using green card special ability: " + getSpecialAbility());
    }
}
