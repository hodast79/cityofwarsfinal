package model;

public class HPBoostCard extends SpecialCard {
    public HPBoostCard(String name, String description, int defenseAttack, int duration, int playerDamage, int maxLevel, int upgradeCost) {
        super(name, description, defenseAttack, duration, playerDamage, maxLevel, upgradeCost);
    }

    @Override
    public void activateAbility(Player player, Player opponent) {
        player.setHp(player.getHp() + this.defenseAttack);
    }
}
