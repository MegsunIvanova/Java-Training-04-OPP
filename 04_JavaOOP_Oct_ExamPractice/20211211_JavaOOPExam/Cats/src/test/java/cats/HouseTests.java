package cats;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class HouseTests {
    House house;
    Cat cat1;
    Cat cat2;
    Cat cat3;
    Cat cat4;
    private static final int CAPACITY = 3;
    private static final String NAME = "HouseForPersian";


    @Before
    public void setUp() {
        house = new House("HouseForPersian", CAPACITY);
        cat1 = new Cat("Matsa");
        cat2 = new Cat("Pinko");
        cat3 = new Cat("Chico");
        cat4 = new Cat("Sisi");
    }

    @Test
    public void testCreateNewHouse() {
        assertEquals(CAPACITY, house.getCapacity());
        assertEquals(NAME, house.getName());
    }

    @Test(expected = NullPointerException.class)
    public void testSetNameShouldThrowWithNullName() {
        new House(null, 3);
    }

    @Test(expected = NullPointerException.class)
    public void testSetNameShouldThrowWithEmptyName() {
        new House(" ", 3);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetCapacityShouldThrowWithNegativeNumber() {
        new House(NAME, -1);
    }

    @Test
    public void testAddCatShouldAddCatToHouse() {
        assertEquals(0, house.getCount());
        house.addCat(cat1);
        assertEquals(1, house.getCount());
        house.addCat(cat2);
        assertEquals(2, house.getCount());
        house.addCat(cat3);
        assertEquals(3, house.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddCatShouldThrowWheAddMoreCatsThanCapacity() {
        house.addCat(cat1);
        house.addCat(cat2);
        house.addCat(cat3);
        house.addCat(cat4);
    }

    @Test
    public void testRemoveShouldRemoveCatFromHouse() {
        house.addCat(cat1);
        house.addCat(cat2);
        house.addCat(cat3);

        assertEquals(3, house.getCount());

        house.removeCat(cat2.getName());

        assertEquals(2, house.getCount());

    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveShouldThrowIfNoSuchCat() {
        house.addCat(cat1);
        house.addCat(cat2);

        house.removeCat(cat2.getName());
        house.removeCat(cat2.getName());
    }

    @Test
    public void testCatForSaleShouldReturnTheCat() {
        house.addCat(cat1);
        house.addCat(cat2);
        house.addCat(cat3);

        Cat cat = house.catForSale(cat1.getName());

        assertEquals(cat1.getName(), cat.getName());

    }

    @Test
    public void testCatForSaleShouldReturnCatWithFalseIsHungry() {
        house.addCat(cat1);
        house.addCat(cat2);
        house.addCat(cat3);

        Cat cat = house.catForSale(cat1.getName());

        assertFalse(cat.isHungry());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCatForSaleShouldThrowIfNoSuchCat() {
        house.addCat(cat1);
        house.addCat(cat2);

        house.catForSale(cat3.getName());
    }
}
