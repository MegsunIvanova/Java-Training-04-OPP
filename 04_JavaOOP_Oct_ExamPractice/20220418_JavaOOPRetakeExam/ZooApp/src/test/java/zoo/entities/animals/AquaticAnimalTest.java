package zoo.entities.animals;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AquaticAnimalTest {
    public static final double INITIAL_KG = 2.50;
    public static final double KG_INCREASE_WHEN_EAT = 7.50;
    public static final String NAME = "Nemo";
    public static final String KIND = "fish";
    public static final double PRICE = 100.00;
    private Animal aquaticAnimal;

    @Before
    public void setUp() {
        aquaticAnimal = new AquaticAnimal(NAME, KIND, PRICE);
    }

    @Test
    public void testAquaticAnimalConstructorShouldCreateAnimal() {
        BaseAnimal animal = new AquaticAnimal(NAME, KIND, PRICE);
        assertEquals(AquaticAnimal.class, animal.getClass());
        assertEquals(NAME, animal.getName());
        assertEquals(KIND, animal.getKind());
        assertEquals(INITIAL_KG, animal.getKg(), 0.00);
        assertEquals(PRICE, animal.getPrice(), 0.00);
    }

    @Test(expected = NullPointerException.class)
    public void testAquaticAnimalConstructorShouldTrowWithNullName() {
        BaseAnimal animal = new AquaticAnimal(null, KIND, PRICE);
    }

    @Test(expected = NullPointerException.class)
    public void testAquaticAnimalConstructorShouldTrowWithWhitespaceName() {
        BaseAnimal animal = new AquaticAnimal(" ", KIND, PRICE);
    }

    @Test(expected = NullPointerException.class)
    public void testAquaticAnimalConstructorShouldTrowWithNullKind() {
        BaseAnimal animal = new AquaticAnimal(NAME, null, PRICE);
    }

    @Test(expected = NullPointerException.class)
    public void testAquaticAnimalConstructorShouldTrowWithWhitespaceKind() {
        BaseAnimal animal = new AquaticAnimal(NAME, " ", PRICE);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAquaticAnimalConstructorShouldTrowWithPriceZero() {
        BaseAnimal animal = new AquaticAnimal(NAME, KIND, 0.00);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAquaticAnimalConstructorShouldTrowWithNegativePrice() {
        BaseAnimal animal = new AquaticAnimal(NAME, KIND, -1.00);
    }

    @Test
    public void testEatShouldIncreaseKg() {
        aquaticAnimal.eat();
        assertEquals(INITIAL_KG + KG_INCREASE_WHEN_EAT, aquaticAnimal.getKg(), 0.00);
        aquaticAnimal.eat();
        assertEquals(INITIAL_KG + KG_INCREASE_WHEN_EAT * 2, aquaticAnimal.getKg(), 0.00);
    }


}