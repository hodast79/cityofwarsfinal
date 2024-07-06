package model;

import java.util.ArrayList;
import java.util.List;

public class Clan {
    private String name;
    private String code;
    private List<Player> members;

    public Clan(String name, String code) {
        this.name = name;
        this.code = code;
        this.members = new ArrayList<>();
    }

    public void addMember(Player player) {
        members.add(player);
    }

    public List<Player> getMembers() {
        return members;
    }

    // Other clan-related methods
}
