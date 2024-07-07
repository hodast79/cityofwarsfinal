package model;

public abstract class SpecialCard {
    private String name;
    private String specialAbility;
    private String description;

    public SpecialCard(String name, String specialAbility, String description) {
        this.name = name;
        this.specialAbility = specialAbility;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecialAbility() {
        return specialAbility;
    }

    public void setSpecialAbility(String specialAbility) {
        this.specialAbility = specialAbility;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public abstract void useSpecialAbility(Player currentPlayer, Player opponent);
}
