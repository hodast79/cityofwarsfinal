package model;

public class CopyCard extends SpecialCard {
    public CopyCard() {
        super("CopyCard", "specialAbility", "description", "Warrior");
    }


    @Override
    public void useSpecialAbility(Player currentPlayer, Player opponent) {
        // Implementation of the special ability
        System.out.println("Using green card special ability: " + getSpecialAbility());
    }
}
