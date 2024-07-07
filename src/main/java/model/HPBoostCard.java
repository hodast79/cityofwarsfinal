package model;

public class HPBoostCard extends SpecialCard {
    public HPBoostCard(String name, String specialAbility, String description) {
        super(name, specialAbility, description);
    }


    @Override
    public void useSpecialAbility() {
        // Implementation of the special ability
        System.out.println("Using green card special ability: " + getSpecialAbility());
    }
}
