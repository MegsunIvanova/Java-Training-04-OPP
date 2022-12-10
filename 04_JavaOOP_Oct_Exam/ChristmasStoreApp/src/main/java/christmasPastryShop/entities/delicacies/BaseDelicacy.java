package christmasPastryShop.entities.delicacies;

import christmasPastryShop.entities.delicacies.interfaces.Delicacy;

import static christmasPastryShop.common.DataValidator.*;
import static christmasPastryShop.common.ExceptionMessages.*;

public abstract class BaseDelicacy implements Delicacy {
    private String name;
    private double portion;
    private double price;

    protected BaseDelicacy(String name, double portion, double price) {
        setName(name);
        setPortion(portion);
        setPrice(price);
    }

    private void setName(String name) {
        validateStringIsNotNullOrBlank(name, INVALID_NAME);
        this.name = name;
    }

    private void setPortion(double portion) {
        validateNumberIsNotLessOrEqualsToZero(portion, INVALID_PORTION);
        this.portion = portion;
    }

    private void setPrice(double price) {
        validateNumberIsNotLessOrEqualsToZero(price, INVALID_PRICE);
        this.price = price;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public double getPortion() {
        return this.portion;
    }

    @Override
    public double getPrice() {
        return this.price;
    }

    @Override
    public String toString() {
        return String.format("%s: %.2fg - %.2f", this.name, this.portion, this.price);
    }
}
