package farmville;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class FarmvilleTests {

    private Farm farm;
    private Animal cow;
    private Animal goat;
    private Animal sheep;
    private Animal buffalo;

    @Before
    public void setUp() {
        farm = new Farm("Test_Name", 3);

        cow = new Animal("cow", 180.10);
        goat = new Animal("goat", 90.00);
        sheep = new Animal("sheep", 100.00);
        sheep = new Animal("buffalo", 200.00);
    }

    @Test
    public void testCreateFarm() {
        assertEquals("Test_Name", farm.getName());
        assertEquals(3, farm.getCapacity());
        assertEquals(0, farm.getCount());
    }

    @Test(expected = NullPointerException.class)
    public void testConstructorShouldThrowWithNullName() {
        new Farm(null, 3);
    }

    @Test(expected = NullPointerException.class)
    public void testConstructorShouldThrowWithEmptyName() {
        new Farm(" ", 3);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorShouldThrowWithNegativeCapacity() {
        new Farm("Test_Name", -1);
    }

    @Test
    public void testAddAnimalShouldAdd() {
        assertEquals(0, farm.getCount());
        farm.add(cow);
        assertEquals(1, farm.getCount());
        farm.add(sheep);
        assertEquals(2, farm.getCount());
        farm.add(goat);
        assertEquals(3, farm.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddAnimalShouldThrowIfIsFullCapacity() {
        farm.add(cow);
        farm.add(sheep);
        farm.add(goat);
        farm.add(buffalo);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddAnimalShouldThrowIfAnimalExists() {
        farm.add(cow);
        farm.add(cow);
    }

    @Test
    public void testRemoveShouldRemoveExistingAnimal() {
        farm.add(cow);
        farm.add(sheep);
        farm.add(goat);

        assertTrue(farm.remove(sheep.getType()));
        assertEquals(2, farm.getCount());
    }

    @Test (expected = NullPointerException.class)
    public void testRemoveShouldThrowIfNoSuchAnimal () {
        farm.add(cow);
        farm.add(sheep);
        farm.add(goat);

        farm.remove(buffalo.getType());
    }
}
