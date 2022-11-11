package theme_05_Polymorphism.Exercises.P01_Vehicles;

public class Truck extends Vehicle {

    public Truck(double fuelQuantity, double fuelConsumptionPerKm) {
        super(fuelQuantity, fuelConsumptionPerKm);
        setSummerIncrease(1.60);
        setFuelLoss(0.95);
    }
}
