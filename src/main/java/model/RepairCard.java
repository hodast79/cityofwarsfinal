package model;

public class RepairCard extends SpecialCard {
    public RepairCard(String name, String description, int defenseAttack, int duration, int playerDamage, int maxLevel, int upgradeCost) {
        super(name, description, defenseAttack, duration, playerDamage, maxLevel, upgradeCost);
    }

    @Override
    public void activateAbility(Player player, Player opponent) {
        // Repairs holes on the playing field.
    }
}
