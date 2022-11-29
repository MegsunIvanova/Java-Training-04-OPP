package zoo.entities.animals;

public class AquaticAnimal extends BaseAnimal {
    private static final double AQUATIC_ANIMAL_INITIAL_KG = 2.50;
    private static final double AQUATIC_ANIMAL_KG_INCREASE_AMOUNT_WHEN_EAT = 7.50;
    private static final String AQUATIC_ANIMAL_LIVE_AREA = "WaterArea";

    public AquaticAnimal(String name, String kind, double price) {
        super(name, kind, AQUATIC_ANIMAL_INITIAL_KG, price);
    }

    @Override
    public void eat() {
        this.setKg(getKg() + AQUATIC_ANIMAL_KG_INCREASE_AMOUNT_WHEN_EAT);
    }
}
