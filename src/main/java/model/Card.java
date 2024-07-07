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

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDefenseAttack(int defenseAttack) {
        this.defenseAttack = defenseAttack;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setPlayerDamage(int playerDamage) {
        this.playerDamage = playerDamage;
    }

    public void setMaxLevel(int maxLevel) {
        this.maxLevel = maxLevel;
    }

    public void setUpgradeCost(int upgradeCost) {
        this.upgradeCost = upgradeCost;
    }

    public abstract void activateAbility(Player player, Player opponent);
}
