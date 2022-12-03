package football.entities.player;

import football.common.ExceptionMessages;
import football.common.Utilities;

public abstract class BasePlayer implements Player {

    private String name;

    private String nationality;

    private double kg;

    private int strength;

    protected BasePlayer(String name, String nationality, double kg, int strength) {
        setName(name);
        setNationality(nationality);
        this.kg = kg;
        setStrength(strength);
    }

    @Override
    public void setName(String name) {
        if (Utilities.isNullOrEmptyString(name)) {
            throw new NullPointerException(ExceptionMessages.PLAYER_NAME_NULL_OR_EMPTY);
        }

        this.name = name;
    }

    private void setNationality(String nationality) {
        if (Utilities.isNullOrEmptyString(nationality)) {
            throw new NullPointerException(ExceptionMessages.PLAYER_NATIONALITY_NULL_OR_EMPTY);
        }
        this.nationality = nationality;
    }

    protected void setStrength(int strength) {
        if (strength <= 0) {
            throw new IllegalArgumentException(ExceptionMessages.PLAYER_STRENGTH_BELOW_OR_EQUAL_ZERO);
        }
        this.strength = strength;
    }

    @Override
    public double getKg() {
        return this.kg;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getStrength() {
        return this.strength;
    }

}
