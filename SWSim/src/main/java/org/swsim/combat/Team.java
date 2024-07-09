package org.swsim.combat;

import java.util.HashSet;

public class Team {
    private final String name;
    private final HashSet<Team> allies = new HashSet<>();

    public Team(String name) {
        this.name = name;
    }

    public void addAlly(Team team) {
        allies.add(team);
    }

    public void removeAlly(Team team) {
        allies.remove(team);
    }

    public boolean isAlly(Team team) {
        return allies.contains(team);
    }

    @Override
    public String toString() {
        return "Team " + name;
    }
}
