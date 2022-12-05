package catHouse.core;

import catHouse.entities.cat.Cat;
import catHouse.entities.cat.LonghairCat;
import catHouse.entities.cat.ShorthairCat;
import catHouse.entities.houses.House;
import catHouse.entities.houses.LongHouse;
import catHouse.entities.houses.ShortHouse;
import catHouse.entities.toys.Ball;
import catHouse.entities.toys.Mouse;
import catHouse.entities.toys.Toy;
import catHouse.repositories.Repository;
import catHouse.repositories.ToyRepository;


import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static catHouse.common.ConstantMessages.*;
import static catHouse.common.ExceptionMessages.*;

public class ControllerImpl implements Controller {

    private Repository toys;
    private Map<String, House> housesByName;

    public ControllerImpl() {
        toys = new ToyRepository();
        housesByName = new LinkedHashMap<>();
    }

    @Override
    public String addHouse(String type, String name) {

        House house;
        switch (type) {
            case "ShortHouse":
                house = new ShortHouse(name);
                break;
            case "LongHouse":
                house = new LongHouse(name);
                break;
            default:
                throw new NullPointerException(INVALID_HOUSE_TYPE);
        }

        housesByName.put(name, house);

        return String.format(SUCCESSFULLY_ADDED_HOUSE_TYPE, type);
    }

    @Override
    public String buyToy(String type) {
        Toy toy;
        switch (type) {
            case "Ball":
                toy = new Ball();
                break;
            case "Mouse":
                toy = new Mouse();
                break;
            default:
                throw new IllegalArgumentException(INVALID_TOY_TYPE);
        }

        this.toys.buyToy(toy);

        return String.format(SUCCESSFULLY_ADDED_TOY_TYPE, type);
    }

    @Override
    public String toyForHouse(String houseName, String toyType) {
        Toy toyForHouse = toys.findFirst(toyType);

        if (toyForHouse == null) {
            throw new IllegalArgumentException(String.format(NO_TOY_FOUND, toyType));
        }

        House house = housesByName.get(houseName);

        house.buyToy(toyForHouse);

        toys.removeToy(toyForHouse);

        return String.format(SUCCESSFULLY_ADDED_TOY_IN_HOUSE, toyType, houseName);
    }

    @Override
    public String addCat(String houseName, String catType, String catName, String catBreed, double price) {
        Cat cat;
        switch (catType) {
            case "ShorthairCat":
                cat = new ShorthairCat(catName, catBreed, price);
                break;
            case "LonghairCat":
                cat = new LonghairCat(catName, catBreed, price);
                break;
            default:
                throw new IllegalArgumentException(INVALID_CAT_TYPE);
        }

        House house = this.housesByName.get(houseName);

        String output;

        if (!isSuitableHouse(catType, house)) {
            output = UNSUITABLE_HOUSE;
        } else {
            house.addCat(cat);
            output = String.format(SUCCESSFULLY_ADDED_CAT_IN_HOUSE, catType, houseName);
        }

        return output;
    }

    private boolean isSuitableHouse(String catType, House house) {
        String houseType = house.getClass().getSimpleName();

        if (catType.equals("ShorthairCat") && houseType.equals("ShortHouse")) {
            return true;
        } else if (catType.equals("LonghairCat") && houseType.equals("LongHouse")) {
            return true;
        }

        return false;
    }

    @Override
    public String feedingCat(String houseName) {
        House house = housesByName.get(houseName);

        house.feeding();

        return String.format(FEEDING_CAT, house.getCats().size());
    }

    @Override
    public String sumOfAll(String houseName) {
        House house = housesByName.get(houseName);

        double valueOfCats = house.getCats().stream()
                .mapToDouble(Cat::getPrice)
                .sum();

        double valueOfToys = house.getToys().stream()
                .mapToDouble(Toy::getPrice)
                .sum();

        double valueOfHouse = valueOfCats + valueOfToys;

        return String.format(VALUE_HOUSE, houseName, valueOfHouse);
    }

    @Override
    public String getStatistics() {
        return housesByName.values()
                .stream()
                .map(House::getStatistics)
                .collect(Collectors.joining(System.lineSeparator()));
    }
}
