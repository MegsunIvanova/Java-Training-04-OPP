package football;

import org.junit.Assert;
import org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class FootballerTest {
    private static final String NAME = "Some footballer name";
    private Footballer footballer;

    @Before
    public void prepare() {
        footballer = new Footballer(NAME);
    }

    @Test
    public void testSetActiveTrueReturnTrue() {
        footballer.setActive(true);
        Assert.assertTrue(footballer.isActive());
    }

    @Test
    public void testSetActiveFalseReturnFalse() {
        footballer.setActive(false);
        Assert.assertFalse(footballer.isActive());
    }

}