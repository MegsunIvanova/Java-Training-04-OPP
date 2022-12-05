package catHouse.entities.houses;

import catHouse.common.NameValidator;
import catHouse.entities.cat.Cat;
import catHouse.entities.toys.Toy;

import java.util.*;
import java.util.stream.Collectors;

import static catHouse.common.ConstantMessages.*;
import static catHouse.common.ExceptionMessages.*;

public abstract class BaseHouse implements House {
    private String name;
    private int capacity;
    private Collection<Toy> toys;
    private Collection<Cat> cats;

    protected BaseHouse(String name, int capacity) {
        setName(name);
        this.capacity = capacity;
        this.toys = new ArrayList<>();
        this.cats = new ArrayList<>();
    }

    @Override
    public void setName(String name) {
        NameValidator.validateName(name, HOUSE_NAME_CANNOT_BE_NULL_OR_EMPTY);

        this.name = name;
    }

    @Override
    public int sumSoftness() {
        return this.toys.stream().mapToInt(Toy::getSoftness).sum();
    }

    @Override
    public void addCat(Cat cat) {
        if (this.cats.size() == capacity) {
            throw new IllegalStateException(NOT_ENOUGH_CAPACITY_FOR_CAT);
        }

        this.cats.add(cat);
    }

    @Override
    public void removeCat(Cat cat) {
        cats.remove(cat);
    }

    @Override
    public void buyToy(Toy toy) {
        toys.add(toy);
    }

    @Override
    public void feeding() {
        cats.forEach(Cat::eating);
    }

    @Override
    public String getStatistics() {
        StringBuilder statistics = new StringBuilder();

        statistics.append(this.name + " " + this.getClass().getSimpleName() + ":")
                .append(System.lineSeparator());

        String catsStatistic = cats.isEmpty() ? "none"
                : cats.stream()
                .map(Cat::getName)
                .collect(Collectors.joining(" "));

        statistics.append("Cats: " + catsStatistic)
                .append(System.lineSeparator());

        statistics.append("Toys: " + toys.size() + " Softness: " + this.sumSoftness());

        return statistics.toString();
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Collection<Cat> getCats() {
        return this.cats;
    }

    @Override
    public Collection<Toy> getToys() {
        return this.toys;
    }
}
