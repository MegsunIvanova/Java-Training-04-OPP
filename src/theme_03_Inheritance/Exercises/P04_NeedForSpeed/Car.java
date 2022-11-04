package theme_03_Inheritance.Exercises.P04_NeedForSpeed;

public class Car extends Vehicle {
    public final static double DEFAULT_FUEL_CONSUMPTION = 3.0;

    public Car(double fuel, int horsePower) {
        super(fuel, horsePower);
        setFuelConsumption(DEFAULT_FUEL_CONSUMPTION);
    }
}
