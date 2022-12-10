package toyStore;

import org.junit.Assert;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;

import static org.junit.Assert.*;

public class ToyStoryTest {
    ToyStore toyStore = new ToyStore();
    Toy toy1 = new Toy("Goki", "1");
    Toy toy2 = new Toy("Vega", "2");
    Toy toy3 = new Toy("Sega", "3");
    Toy toy4 = new Toy("Lego", "4");
    Toy toy5 = new Toy("Duplo", "5");
    Toy toy6 = new Toy("Sony", "6");
    Toy toy7 = new Toy("FisherPrice", "7");

    @Test(expected = IllegalArgumentException.class)
    public void testAddToyShouldThrowIfInvalidShelf() throws OperationNotSupportedException {
        toyStore.addToy("Invalid", toy1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddToyShouldThrowIfShelfIsNotFree() throws OperationNotSupportedException {
        toyStore.addToy("A", toy1);
        toyStore.addToy("A", toy2);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testAddToyShouldThrowIfToyExist() throws OperationNotSupportedException {
        toyStore.addToy("A", toy1);
        toyStore.addToy("B", toy1);
    }

    @Test
    public void testAddToyShouldAddTheToy() throws OperationNotSupportedException {

        String result = toyStore.addToy("A", toy1);
        toyStore.addToy("B", toy2);
        toyStore.addToy("C", toy3);
        toyStore.addToy("D", toy4);
        toyStore.addToy("E", toy5);
        toyStore.addToy("F", toy6);
        toyStore.addToy("G", toy7);

        assertEquals(String.format("Toy:%s placed successfully!", toy1.getToyId()), result);

        assertEquals(toy1, toyStore.getToyShelf().get("A"));
        assertEquals(toy2, toyStore.getToyShelf().get("B"));
        assertEquals(toy3, toyStore.getToyShelf().get("C"));
        assertEquals(toy4, toyStore.getToyShelf().get("D"));
        assertEquals(toy5, toyStore.getToyShelf().get("E"));
        assertEquals(toy6, toyStore.getToyShelf().get("F"));
        assertEquals(toy7, toyStore.getToyShelf().get("G"));

    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveToyShouldThrowIfInvalidShelf() throws OperationNotSupportedException {
        toyStore.addToy("A", toy1);

        toyStore.removeToy("InvalidShelf", toy1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveToyShouldThrowToyIsNotOnShelf() throws OperationNotSupportedException {
        toyStore.addToy("A", toy1);
        toyStore.addToy("B", toy2);

        toyStore.removeToy("B", toy1);
    }

    @Test
    public void testRemoveToyShouldRemove() throws OperationNotSupportedException {
        toyStore.addToy("A", toy1);

        String result = toyStore.removeToy("A", toy1);
        assertEquals(String.format("Remove toy:%s successfully!", toy1.getToyId()), result);

        assertNull(toyStore.getToyShelf().get("A"));
        assertNull(toyStore.getToyShelf().get("B"));
        assertNull(toyStore.getToyShelf().get("C"));
        assertNull(toyStore.getToyShelf().get("D"));
        assertNull(toyStore.getToyShelf().get("E"));
        assertNull(toyStore.getToyShelf().get("F"));
        assertNull(toyStore.getToyShelf().get("G"));
    }

}
