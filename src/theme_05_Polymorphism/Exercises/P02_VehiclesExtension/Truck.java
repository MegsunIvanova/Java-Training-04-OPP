package theme_05_Polymorphism.Exercises.P02_VehiclesExtension;

public class Truck extends Vehicle {

    public Truck(double fuelQuantity, double fuelConsumptionPerKm, double tankCapacity) {
        super(fuelQuantity, fuelConsumptionPerKm, tankCapacity);
        setConsumptionIncrease(1.60);
        setFuelLoss(0.95);
    }
}
