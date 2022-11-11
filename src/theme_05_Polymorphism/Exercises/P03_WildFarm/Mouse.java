package theme_05_Polymorphism.Exercises.P03_WildFarm;

public class Mouse extends Mammal {


    public Mouse(String animalName,Double animalWeight, String livingRegion) {
        super(animalName, "Mouse", animalWeight, livingRegion);
    }

    @Override
    public void makeSound() {
        System.out.println("SQUEEEAAAK!");
    }

    @Override
    void eat(Food food) {
        if (food instanceof Vegetable) {
            setFoodEaten(getFoodEaten() + food.getQuantity());
        } else {
            System.out.println("Mice are not eating that type of food!");
        }

    }

}
