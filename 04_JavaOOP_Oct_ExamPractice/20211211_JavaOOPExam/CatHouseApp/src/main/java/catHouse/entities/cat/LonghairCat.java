package catHouse.entities.cat;

public class LonghairCat extends BaseCat {
    private static final int KILOGRAMS = 9;
    private static final int WEIGHT_INCREASE_WHEN_EATING = 3;

    public LonghairCat(String name, String breed, double price) {
        super(name, breed, price);
        setKilograms(KILOGRAMS);
    }

    @Override
    public void eating() {
        this.setKilograms(getKilograms() + WEIGHT_INCREASE_WHEN_EATING);
    }
}
