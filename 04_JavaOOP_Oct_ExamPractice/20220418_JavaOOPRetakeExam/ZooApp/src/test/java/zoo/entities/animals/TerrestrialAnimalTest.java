package zoo.entities.animals;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TerrestrialAnimalTest {
    public static final double INITIAL_KG = 5.50;
    public static final double KG_INCREASE_WHEN_EAT = 5.70;
    public static final String NAME = "Dino";
    public static final String KIND = "Wolf";
    public static final double PRICE = 1000.00;
    private Animal terrestrialAnimal;

    @Before
    public void setUp() {
        terrestrialAnimal = new TerrestrialAnimal(NAME, KIND, PRICE);
    }

    @Test
    public void testAquaticAnimalConstructorShouldCreateAnimal() {
        BaseAnimal animal = new TerrestrialAnimal(NAME, KIND, PRICE);
        assertEquals(TerrestrialAnimal.class, animal.getClass());
        assertEquals(NAME, animal.getName());
        assertEquals(KIND, animal.getKind());
        assertEquals(INITIAL_KG, animal.getKg(), 0.00);
        assertEquals(PRICE, animal.getPrice(), 0.00);
    }

    @Test(expected = NullPointerException.class)
    public void testAquaticAnimalConstructorShouldTrowWithNullName() {
        BaseAnimal animal = new TerrestrialAnimal(null, KIND, PRICE);
    }

    @Test(expected = NullPointerException.class)
    public void testAquaticAnimalConstructorShouldTrowWithWhitespaceName() {
        BaseAnimal animal = new TerrestrialAnimal(" ", KIND, PRICE);
    }

    @Test(expected = NullPointerException.class)
    public void testAquaticAnimalConstructorShouldTrowWithNullKind() {
        BaseAnimal animal = new TerrestrialAnimal(NAME, null, PRICE);
    }

    @Test(expected = NullPointerException.class)
    public void testAquaticAnimalConstructorShouldTrowWithWhitespaceKind() {
        BaseAnimal animal = new TerrestrialAnimal(NAME, " ", PRICE);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAquaticAnimalConstructorShouldTrowWithPriceZero() {
        BaseAnimal animal = new TerrestrialAnimal(NAME, KIND, 0.00);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAquaticAnimalConstructorShouldTrowWithNegativePrice() {
        BaseAnimal animal = new TerrestrialAnimal(NAME, KIND, -1.00);
    }

    @Test
    public void testEatShouldIncreaseKg() {
        terrestrialAnimal.eat();
        assertEquals(INITIAL_KG + KG_INCREASE_WHEN_EAT, terrestrialAnimal.getKg(), 0.00);
        terrestrialAnimal.eat();
        assertEquals(INITIAL_KG + KG_INCREASE_WHEN_EAT * 2, terrestrialAnimal.getKg(), 0.00);
    }
}