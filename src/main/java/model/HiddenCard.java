package model;

public class HiddenCard extends SpecialCard {
    public HiddenCard(String name, String specialAbility, String description) {
        super(name, specialAbility, description);
    }


    @Override
    public void useSpecialAbility() {
        // Implementation of the special ability
        System.out.println("Using green card special ability: " + getSpecialAbility());
    }
}
