package model;

public abstract class SpecialCard extends Card {
    public SpecialCard(String name, String description, int defenseAttack, int duration, int playerDamage, int maxLevel, int upgradeCost) {
        super(name, description, defenseAttack, duration, playerDamage, maxLevel, upgradeCost);
    }
}
