package model;

public class Card {
    private String name;
    private int defenseAttack;
    private int duration;
    private String character;
    private int playerDamage;

    //private int level;        // Current level of the card
    private int upgradeCost;  // Cost to upgrade the card to the next level
    private int maxLevel;     // Maximum level the card can reach
    private String description;

    // Constructor with the correct arguments
    public Card(String name, int defenseAttack,String character ,int duration, int playerDamage, int upgradeCost, int maxLevel, String description) {
        this.name = name;
        this.defenseAttack = defenseAttack;
        this.duration = duration;
        this.playerDamage = playerDamage;
        this.upgradeCost = upgradeCost;
        this.maxLevel = maxLevel;
        this.description = description;
        this.character =character;
    }

    public Card(String name, String character, int defenseAttack, int duration, int playerDamage) {
        this.name = name;
        this.character = character;
        this.defenseAttack = defenseAttack;
        this.duration = duration;
        this.playerDamage = playerDamage;
        this.upgradeCost = 0;
        this.maxLevel = 0;
        this.description = null;
    }

    public Card() {}



    @Override
    public String toString() {
        return "Card{" +
                "name='" + name + '\'' +
                ", defenseAttack=" + defenseAttack +
                ", duration=" + duration +
                ", playerDamage=" + playerDamage +
                ", character(type)='" + character + '\'' +
                '}';
    }


    public void displayInfo() {
        System.out.println("Name: " + name);
        System.out.println("DefenseAttack: " + defenseAttack);
        System.out.println("Duration: " + duration);
        System.out.println("Player Damage: " + playerDamage);
        System.out.println("Upgrade Level: " + maxLevel);
        System.out.println("Upgrade Cost: " + upgradeCost);
        System.out.println("Description: " + description);
    }

    // Method to upgrade card
    public void upgrade() {
        // this.maxLevel++;
        this.defenseAttack *= 1.25;
        this.upgradeCost *= 1.25;
    }
    public String getName() {
        return name;
    }



    public int getDuration() {
        return duration;
    }

    public int getPlayerDamage() {
        return playerDamage;
    }


    public int getUpgradeCost() {
        return upgradeCost;
    }

    /*
    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

     */

    public int getDefenseAttack() {
        return defenseAttack;
    }

    public void setDefenseAttack(int defenseAttack) {
        this.defenseAttack = defenseAttack;
    }

    public int getMaxLevel() {
        return maxLevel;
    }

    public void setMaxLevel(int maxLevel) {
        this.maxLevel = maxLevel;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setPlayerDamage(int playerDamage) {
        this.playerDamage = playerDamage;
    }


    public void setUpgradeCost(int upgradeCost) {
        this.upgradeCost = upgradeCost;
    }
}
