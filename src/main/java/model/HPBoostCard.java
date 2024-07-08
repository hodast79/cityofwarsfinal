package model;

public class HPBoostCard extends SpecialCard {
    private int hpBoostAmount = 50;

    public HPBoostCard() {
        super("HPBoostCard", "specialAbility", "description", "Warrior");
    }

    public int getHpBoostAmount() {
        return hpBoostAmount;
    }

    public void setHpBoostAmount(int hpBoostAmount) {
        this.hpBoostAmount = hpBoostAmount;
    }

    @Override
    public void useSpecialAbility(Player currentPlayer, Player opponent) {
        // Boost the HP of the current player
        currentPlayer.setHp(currentPlayer.getHp() + hpBoostAmount);
        System.out.println(currentPlayer.getUsername() + " used " + getName() + " and gained " + hpBoostAmount + " HP!");
    }
}
