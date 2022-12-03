package fairyShop.models;

import static fairyShop.common.ExceptionMessages.*;

public class PresentImpl implements Present {
    private final int ENERGY_REDUCER_WHEN_CRAFTED = 10;
    private String name;
    private int energyRequired;

    public PresentImpl(String name, int energyRequired) {
        setName(name);
        setEnergyRequired(energyRequired);
    }

    private void setName(String name) {

        if (name == null || name.trim().isEmpty()) {
            throw new NullPointerException(PRESENT_NAME_NULL_OR_EMPTY);
        }

        this.name = name;
    }

    private void setEnergyRequired(int energyRequired) {

        if (energyRequired < 0) {
            throw new IllegalArgumentException(PRESENT_ENERGY_LESS_THAN_ZERO);
        }

        this.energyRequired = energyRequired;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getEnergyRequired() {
        return this.energyRequired;
    }

    @Override
    public void getCrafted() {

        int newEnergyRequired = getEnergyRequired() - ENERGY_REDUCER_WHEN_CRAFTED;

        setEnergyRequired(Math.max(0, newEnergyRequired));
    }

    @Override
    public boolean isDone() {
        return getEnergyRequired() == 0;
    }

}
