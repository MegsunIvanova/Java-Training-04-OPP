package glacialExpedition.models.explorers;

import glacialExpedition.common.DataValidator;
import glacialExpedition.models.suitcases.Carton;
import glacialExpedition.models.suitcases.Suitcase;

import static glacialExpedition.common.ExceptionMessages.*;

public abstract class BaseExplorer implements Explorer {
    private static final double ENERGY_REDUCER_WHEN_SEARCH = 15.00;

    private String name;
    private double energy;
    private Suitcase suitcase;

    protected BaseExplorer(String name, double energy) {
        setName(name);
        setEnergy(energy);
        this.suitcase = new Carton();
    }

    private void setName(String name) {
        DataValidator.ensureNotNullOrEmptyString(name, EXPLORER_NAME_NULL_OR_EMPTY);
        this.name = name;
    }

    protected void setEnergy(double energy) {
        DataValidator.ensureNotNegativeNumber(energy, EXPLORER_ENERGY_LESS_THAN_ZERO);
        this.energy = energy;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public double getEnergy() {
        return this.energy;
    }

    @Override
    public boolean canSearch() {
        return this.energy > 0;
    }

    @Override
    public Suitcase getSuitcase() {
        return this.suitcase;
    }

    @Override
    public void search() {
        this.energy = Math.max(0, this.energy - ENERGY_REDUCER_WHEN_SEARCH);
    }
}
