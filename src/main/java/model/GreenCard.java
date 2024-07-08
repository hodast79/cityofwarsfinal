package model;

public class GreenCard extends SpecialCard {

    public GreenCard() {
        super("GreenCard", "specialAbility", "description", "Assassin");
    }

    @Override
    public void useSpecialAbility(Player currentPlayer, Player opponent) {
        // Implementation of the special ability
        System.out.println("Using green card special ability: " + getSpecialAbility());
    }
}
