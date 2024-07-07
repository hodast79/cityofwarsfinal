package model;

public class RemoveCardFromHand extends SpecialCard {
    public RemoveCardFromHand(String name, String description, int defenseAttack, int duration, int playerDamage, int maxLevel, int upgradeCost) {
        super(name, description, defenseAttack, duration, playerDamage, maxLevel, upgradeCost);
    }

    @Override
    public void activateAbility(Player player, Player opponent) {
        // Removes a card from opponent's hand.
    }
}
