package fairyShop.models;

public class Sleepy extends BaseHelper {
    private static final int INITIAL_ENERGY = 50;
    private final int SLEEPY_ENERGY_FOR_WORK = 15;

    public Sleepy(String name) {
        super(name, INITIAL_ENERGY);
    }

    @Override
    public void work() {
        int newEnergy = getEnergy() - SLEEPY_ENERGY_FOR_WORK;
        setEnergy(Math.max(0, newEnergy));
    }

}
