package zoo.entities.areas;

import zoo.common.ExceptionMessages;
import zoo.common.Utilities;
import zoo.entities.animals.Animal;
import zoo.entities.foods.Food;

import java.util.*;
import java.util.stream.Collectors;

public abstract class BaseArea implements Area {

    private String name;
    private int capacity;
    private Collection<Food> foods;
    private Collection<Animal> animals;

    protected BaseArea(String name, int capacity) {
        setName(name);
        this.capacity = capacity;
        this.foods = new ArrayList<>();
        this.animals = new ArrayList<>();
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Collection<Animal> getAnimals() {
        return this.animals;
    }

    @Override
    public Collection<Food> getFoods() {
        return this.foods;
    }

    @Override
    public int sumCalories() {
        return foods.stream()
                .mapToInt(Food::getCalories)
                .sum();
    }

    @Override
    public void addAnimal(Animal animal) {
        if (this.animals.size() >= this.capacity) {
            throw new IllegalStateException(ExceptionMessages.NOT_ENOUGH_CAPACITY);
        }
        animals.add(animal);
    }

    @Override
    public void removeAnimal(Animal animal) {
        this.animals.remove(animal);
    }

    @Override
    public void addFood(Food food) {
        this.foods.add(food);
    }

    @Override
    public void feed() {
        animals.forEach(Animal::eat);
    }

    @Override
    public String getInfo() {
        StringBuilder out = new StringBuilder();
        out.append(String.format("%s (%s):", this.getName(), this.getClass().getSimpleName()))
                .append(System.lineSeparator());

        out.append("Animals: ");
        if (animals.isEmpty()) {
            out.append("none");
        } else {
            out.append(animals.stream()
                    .map(Animal::getName)
                    .collect(Collectors.joining(" ")));
        }
        out.append(System.lineSeparator());

        out.append(String.format("Foods: %d", foods.size())).append(System.lineSeparator());

        out.append(String.format("Calories: %d", sumCalories()));

        return out.toString();
    }

    public void setName(String name) {
        Utilities.ensureStringIsNotNullOrEmpty(name, ExceptionMessages.AREA_NAME_NULL_OR_EMPTY);
        this.name = name;
    }
}
