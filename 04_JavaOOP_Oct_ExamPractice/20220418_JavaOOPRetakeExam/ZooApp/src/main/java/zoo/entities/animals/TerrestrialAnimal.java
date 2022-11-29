package zoo.entities.animals;

public class TerrestrialAnimal extends BaseAnimal {
    private static final double TERRESTRIAL_ANIMAL_INITIAL_KG = 5.50;
    private static final double TERRESTRIAL_ANIMAL_KG_INCREASE_AMOUNT_WHEN_EAT = 5.70;
    private static final String TERRESTRIAL_ANIMAL_LIVE_AREA = "LandArea";

    public TerrestrialAnimal(String name, String kind, double price) {
        super(name, kind, TERRESTRIAL_ANIMAL_INITIAL_KG, price);
    }

    @Override
    public void eat() {

        this.setKg(getKg() + TERRESTRIAL_ANIMAL_KG_INCREASE_AMOUNT_WHEN_EAT);
    }
}
