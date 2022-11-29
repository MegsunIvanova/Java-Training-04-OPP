package zoo.entities.foods;

import org.junit.Assert;
import org.junit.Test;

public class MeatTest {
    public static final int CALORIES = 70;
    public static final double PRICE = 10.00;

    @Test
    public void testConstructorOfMeat() {
        BaseFood meat = new Meat();
        Assert.assertEquals(Meat.class, meat.getClass());
        Assert.assertEquals(CALORIES, meat.getCalories());
        Assert.assertEquals(PRICE, meat.getPrice(), 0.00);
    }

}