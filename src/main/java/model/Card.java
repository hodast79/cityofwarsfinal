package model;

public abstract class Card {
    protected String name;
    protected String description;
    protected int defenseAttack;
    protected int duration;
    protected int playerDamage;
    protected int maxLevel;
    protected int upgradeCost;

    public Card(String name, String description, int defenseAttack, int duration, int playerDamage, int maxLevel, int upgradeCost) {
        this.name = name;
        this.description = description;
        this.defenseAttack = defenseAttack;
        this.duration = duration;
        this.playerDamage = playerDamage;
        this.maxLevel = maxLevel;
        this.upgradeCost = upgradeCost;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getDefenseAttack() {
        return defenseAttack;
    }

    public int getDuration() {
        return duration;
    }

    public int getPlayerDamage() {
        return playerDamage;
    }

    public int getMaxLevel() {
        return maxLevel;
    }

    public int getUpgradeCost() {
        return upgradeCost;
    }

    public abstract void activateAbility(Player player, Player opponent);
}
