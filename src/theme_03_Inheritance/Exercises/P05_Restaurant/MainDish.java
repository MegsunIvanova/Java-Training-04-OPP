package theme_03_Inheritance.Exercises.P05_Restaurant;

import java.math.BigDecimal;

public class MainDish extends Food {

    public MainDish(String name, BigDecimal price, double grams) {
        super(name, price, grams);
    }
}
