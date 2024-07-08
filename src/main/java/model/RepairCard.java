package model;

public class RepairCard extends SpecialCard {
    public RepairCard() {
        super("RepairCard", "specialAbility", "description", "Warrior");
    }

    @Override
    public void useSpecialAbility(Player currentPlayer, Player opponent) {
        // Check if there is an unaccessible block to repair
        if (currentPlayer.getGameController().getUnaccessibleBlock() == -1) {
            System.out.println("No unaccessible block to repair.");
            return;
        }

        // Repair the unaccessible block
        int repairedBlock = currentPlayer.getGameController().getUnaccessibleBlock();
        currentPlayer.getGameController().setUnaccessibleBlock(-1); // Mark the block as accessible

        System.out.println(currentPlayer.getUsername() + " used " + getName() + " to repair block " + (repairedBlock + 1) + " making it accessible.");
    }
}
