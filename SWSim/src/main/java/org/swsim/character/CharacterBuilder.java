package org.swsim.character;

import org.swsim.combat.Team;

public class CharacterBuilder {
    Team team;

    public CharacterBuilder(CharacterSheet sheet) {
        fromSheet(sheet);
    }

    private void fromSheet(CharacterSheet sheet) {

    }

    public CharacterBuilder setTeam(Team team) {
        this.team = team;
        return this;
    }

    public Character build() {
        Character newChar = new Character();
        newChar.setTeam(team);
        return newChar;
    }
}
