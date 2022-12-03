package football.entities.supplement;

public class Powdered extends BaseSupplement{
    private static final int POWDERED_ENERGY = 120;
    private static final double POWDERED_PRICE = 15.00;

    public Powdered() {
        super(POWDERED_ENERGY, POWDERED_PRICE);
    }
}
