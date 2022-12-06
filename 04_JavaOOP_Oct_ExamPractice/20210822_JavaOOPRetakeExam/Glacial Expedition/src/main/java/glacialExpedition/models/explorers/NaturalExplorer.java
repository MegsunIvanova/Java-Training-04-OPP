package glacialExpedition.models.explorers;

public class NaturalExplorer extends BaseExplorer {
    private static final double INITIAL_ENERGY = 60.00;
    private static final double ENERGY_REDUCER_WHEN_SEARCH = 7.00;

    public NaturalExplorer(String name) {
        super(name, INITIAL_ENERGY);
    }

    @Override
    public void search() {
        double newEnergy = Math.max(0, this.getEnergy() - ENERGY_REDUCER_WHEN_SEARCH);
        setEnergy(newEnergy);
    }


}
