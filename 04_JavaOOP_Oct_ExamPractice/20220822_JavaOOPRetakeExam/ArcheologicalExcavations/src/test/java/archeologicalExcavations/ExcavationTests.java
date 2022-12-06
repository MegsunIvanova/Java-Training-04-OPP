package archeologicalExcavations;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ExcavationTests {

    private Excavation excavation;
    private Archaeologist pesho;
    private Archaeologist toshko;

    @Before
    public void SetUp() {
        excavation = new Excavation("Pernik", 10);
        pesho = new Archaeologist("Pesho", 10);
        toshko = new Archaeologist("Toshko", 20);
    }

    @Test
    public void testCreateExcavation() {
        assertEquals("Pernik", excavation.getName());
        assertEquals(10, excavation.getCapacity());
        assertEquals(0, excavation.getCount());
    }

    @Test(expected = NullPointerException.class)
    public void constructorShouldThrowWithNullName() {
        new Excavation(null, 10);
    }

    @Test(expected = NullPointerException.class)
    public void constructorShouldThrowWithEmptyName() {
        new Excavation(" ", 10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorShouldThrowWithNegativeCapacity() {
        new Excavation("Pernik", -10);
    }

    @Test
    public void testAddArchaeologistShouldAdd() {
        assertEquals(0, excavation.getCount());
        excavation.addArchaeologist(pesho);
        assertEquals(1, excavation.getCount());
        excavation.addArchaeologist(toshko);
        assertEquals(2, excavation.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddArchaeologistShouldThrowIfNoCapacity() {
        Excavation excavationWithCapacty1 = new Excavation("Pernik2", 1);
        excavationWithCapacty1.addArchaeologist(pesho);
        excavationWithCapacty1.addArchaeologist(toshko);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddArchaeologistShouldThrowIfArchaeologistExist() {
        excavation.addArchaeologist(pesho);
        excavation.addArchaeologist(pesho);
    }

    @Test
    public void testRemoveArchaeologistShouldRemove() {
        excavation.addArchaeologist(pesho);
        excavation.addArchaeologist(toshko);
        assertEquals(2, excavation.getCount());

        boolean result = excavation.removeArchaeologist(pesho.getName());
        assertTrue(result);
        assertEquals(1, excavation.getCount());
    }

    @Test
    public void testRemoveArchaeologistShouldReturnFalseIfNoSuchArchaeologist() {
        excavation.addArchaeologist(pesho);
        excavation.addArchaeologist(toshko);
        boolean result = excavation.removeArchaeologist("MissingName");
        assertFalse(result);
    }


}
