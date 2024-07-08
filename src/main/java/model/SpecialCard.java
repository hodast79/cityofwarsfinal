package model;

public class SpecialCard extends Card {
    private String specialAbility;
    public SpecialCard(String name, int defenseAttack, String character, int duration, int playerDamage, int upgradeCost, int maxLevel, String description, String specialAbility) {
        super(name, 0, character, duration, 0, upgradeCost, maxLevel, description);
        this.specialAbility = specialAbility;
    }

    public SpecialCard(String name, String character, int defenseAttack, int duration, int playerDamage, String specialAbility) {
        super(name, character, 0, duration, 0);
        this.specialAbility = specialAbility;
    }

    public SpecialCard(String name, String specialAbility, String description, String character) {
        super(name, character, 0, 1, 0);
        this.specialAbility = specialAbility;
    }

    public String getSpecialAbility() {
        return specialAbility;
    }

    public void setSpecialAbility(String specialAbility) {
        this.specialAbility = specialAbility;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Special Ability: " + specialAbility);
    }

    public void useSpecialAbility(Player currentPlayer, Player opponent) {
        // Implement the special ability logic in subclasses
    }
}