package model;

public class HiddenCard extends SpecialCard {
    public HiddenCard() {
        super("HiddenCard", "specialAbility", "description", "Archer");
    }

    @Override
    public void useSpecialAbility(Player currentPlayer, Player opponent) {
        // Implementation of the special ability
        System.out.println("Using green card special ability: " + getSpecialAbility());
    }
}
