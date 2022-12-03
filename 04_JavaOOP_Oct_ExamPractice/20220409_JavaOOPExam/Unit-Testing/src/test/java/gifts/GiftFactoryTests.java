package gifts;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class GiftFactoryTests {
    Gift gift1 = new Gift("Big", 1000);
    Gift gift2 = new Gift("Small", 300);
    GiftFactory factory;

    @Before
    public void setUp() {
        factory = new GiftFactory();
    }

    @Test
    public void testCreateGiftShouldAddGift() {
        assertEquals(0, factory.getCount());
        factory.createGift(gift1);
        assertEquals(1, factory.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateGiftShouldThrowIfGiftTypeExist() {
        factory.createGift(gift1);
        factory.createGift(new Gift(gift1.getType(), 500));
    }

    @Test
    public void testRemoveGiftShouldReturnTrueIfGiftExist() {
        factory.createGift(gift1);
        assertEquals(1, factory.getCount());
        assertTrue(factory.removeGift(gift1.getType()));
        assertEquals(0, factory.getCount());
    }

    @Test
    public void testRemoveGiftShouldReturnFalseIfNoSuchGift() {
        factory.createGift(gift1);
        assertEquals(1, factory.getCount());
        assertFalse(factory.removeGift("Some gift"));
        assertEquals(1, factory.getCount());
    }

    @Test(expected = NullPointerException.class)
    public void testRemoveGiftShouldThrowWithNullArgument() {
        factory.removeGift(null);
    }

    @Test(expected = NullPointerException.class)
    public void testRemoveGiftShouldThrowWithEmptyArgument() {
        factory.removeGift("   ");
    }

    @Test
    public void testGetPresentWithLeastMagicShouldReturnGiftWithMinMagic() {
        factory.createGift(gift1);
        factory.createGift(gift2);

        assertEquals(gift2, factory.getPresentWithLeastMagic());

    }

    @Test
    public void testGetPresentWithLeastMagicShouldReturnNullIfEmpty() {
        assertEquals(null, factory.getPresentWithLeastMagic());

    }

    @Test
    public void testGetPresentShouldReturnGiftWithType() {
        factory.createGift(gift1);
        factory.createGift(gift2);

        assertEquals(gift1, factory.getPresent(gift1.getType()));
    }

    @Test
    public void testGetPresentShouldReturnNullIfNoSuchGift() {
        factory.createGift(gift1);

        assertEquals(null, factory.getPresent(gift2.getType()));
    }

    @Test
    public void testGetPresentsShouldReturnCollectionWithCorrectSize() {
        Collection<Gift> presents = factory.getPresents();
        assertEquals(0, presents.size());

        factory.createGift(gift1);
        factory.createGift(gift2);
        Collection<Gift> presents2 = factory.getPresents();
        assertEquals(2, presents2.size());

    }

    @Test(expected = UnsupportedOperationException.class)
    public void testGetPresentsShouldReturnUnmodifiableCollection() {
        factory.createGift(gift1);
        Collection<Gift> presents = factory.getPresents();
        presents.add(gift2);
    }

}
