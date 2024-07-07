package model;

public class ReduceOpponentCard extends SpecialCard {
    public ReduceOpponentCard(String name, String description, int defenseAttack, int duration, int playerDamage, int maxLevel, int upgradeCost) {
        super(name, description, defenseAttack, duration, playerDamage, maxLevel, upgradeCost);
    }

    @Override
    public void activateAbility(Player player, Player opponent) {
        // Reduces the power of an opponent's card.
    }
}
