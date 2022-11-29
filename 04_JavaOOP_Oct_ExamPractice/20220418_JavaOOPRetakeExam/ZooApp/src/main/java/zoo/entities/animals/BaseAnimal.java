package zoo.entities.animals;

import zoo.common.ExceptionMessages;
import zoo.common.Utilities;

public abstract class BaseAnimal implements Animal {
    private String name;
    private String kind;
    private double kg;
    private double price;


    protected BaseAnimal(String name, String kind, double kg, double price) {
        setName(name);
        setKind(kind);
        this.kg = kg;
        setPrice(price);
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public double getKg() {
        return this.kg;
    }

    @Override
    public double getPrice() {
        return this.price;
    }

    public String getKind() {
        return this.kind;
    }

    private void setName(String name) {
        Utilities.ensureStringIsNotNullOrEmpty(name, ExceptionMessages.ANIMAL_NAME_NULL_OR_EMPTY);
        this.name = name;
    }

    private void setKind(String kind) {
        Utilities.ensureStringIsNotNullOrEmpty(kind, ExceptionMessages.ANIMAL_KIND_NULL_OR_EMPTY);
        this.kind = kind;
    }

    public void setPrice(double price) {
        if (price <= 0) {
            throw new IllegalArgumentException(ExceptionMessages.ANIMAL_PRICE_BELOW_OR_EQUAL_ZERO);
        }
        this.price = price;
    }

    protected void increaseKg(double kgToAdd) {
        this.kg += kgToAdd;
    }

    protected void setKg(double kg) {
        this.kg = kg;
    }
}
