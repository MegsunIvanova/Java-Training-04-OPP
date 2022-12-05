package catHouse.entities.cat;

public class ShorthairCat extends BaseCat {
    private static final int KILOGRAMS = 7;
    private static final int WEIGHT_INCREASE_WHEN_EATING = 1;

    public ShorthairCat(String name, String breed, double price) {
        super(name, breed, price);
        setKilograms(KILOGRAMS);
    }

    @Override
    public void eating() {
        this.setKilograms(getKilograms() + WEIGHT_INCREASE_WHEN_EATING);
    }
}
