package theme_02_Encapsulation.Exercises.P04_PizzaCalories;

public class Topping {
    private String toppingType;
    private double weight;

    public Topping(String toppingType, double weight) {
        setToppingType(toppingType);
        setWeight(weight);
    }

    private void setToppingType(String toppingType) {

        switch (toppingType) {
            case "Meat":
            case "Veggies":
            case "Cheese":
            case "Sauce":
                this.toppingType = toppingType;
                break;
            default:
                throw new IllegalStateException(String.format(
                        "Cannot place %s on top of your pizza.", toppingType));
        }
    }

    private void setWeight(double weight) {
        if (weight >= 1 && weight <= 50) {
            this.weight = weight;
        } else {
            throw new IllegalStateException(String.format(
                    "%s weight should be in the range [1..50].", toppingType));
        }
    }

    private double getModifier() {
        switch (toppingType) {
            case "Meat":
                return 1.2;
            case "Veggies":
                return 0.8;
            case "Cheese":
                return 1.1;
            case "Sauce":
                return 0.9;
            default:
                return 0.0;
        }
    }

    public double calculateCalories() {
        return 2 * weight * getModifier();
    }
}
