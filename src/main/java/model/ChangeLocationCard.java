package model;

public class ChangeLocationCard extends SpecialCard {
    public ChangeLocationCard(String name, String description, int defenseAttack, int duration, int playerDamage, int maxLevel, int upgradeCost) {
        super(name, description, defenseAttack, duration, playerDamage, maxLevel, upgradeCost);
    }

    @Override
    public void activateAbility(Player player, Player opponent) {
        // Changes the location of a block.
    }
}
