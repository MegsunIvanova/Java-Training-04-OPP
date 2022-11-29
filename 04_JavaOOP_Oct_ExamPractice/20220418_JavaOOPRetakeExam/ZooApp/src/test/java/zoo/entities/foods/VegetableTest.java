package zoo.entities.foods;

import org.junit.Assert;
import org.junit.Test;

public class VegetableTest {
    public static final int CALORIES = 50;
    public static final double PRICE = 5.00;

    @Test
    public void testConstructorOfVegetable() {
        BaseFood vegetable = new Vegetable();
        Assert.assertEquals(Vegetable.class, vegetable.getClass());
        Assert.assertEquals(CALORIES, vegetable.getCalories());
        Assert.assertEquals(PRICE, vegetable.getPrice(), 0.00);
    }

}