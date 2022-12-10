package christmasPastryShop.core;

import christmasPastryShop.core.interfaces.Controller;
import christmasPastryShop.entities.booths.OpenBooth;
import christmasPastryShop.entities.booths.PrivateBooth;
import christmasPastryShop.entities.cocktails.Hibernation;
import christmasPastryShop.entities.cocktails.MulledWine;
import christmasPastryShop.entities.delicacies.Gingerbread;
import christmasPastryShop.entities.delicacies.Stolen;
import christmasPastryShop.entities.delicacies.interfaces.Delicacy;
import christmasPastryShop.entities.cocktails.interfaces.Cocktail;
import christmasPastryShop.entities.booths.interfaces.Booth;
import christmasPastryShop.repositories.interfaces.BoothRepository;
import christmasPastryShop.repositories.interfaces.CocktailRepository;
import christmasPastryShop.repositories.interfaces.DelicacyRepository;

import static christmasPastryShop.common.ExceptionMessages.*;
import static christmasPastryShop.common.OutputMessages.*;

public class ControllerImpl implements Controller {
    private DelicacyRepository<Delicacy> delicacyRepository;
    private CocktailRepository<Cocktail> cocktailRepository;
    private BoothRepository<Booth> boothRepository;
    private double totalStoreIncome;


    public ControllerImpl(DelicacyRepository<Delicacy> delicacyRepository,
                          CocktailRepository<Cocktail> cocktailRepository,
                          BoothRepository<Booth> boothRepository) {

        this.delicacyRepository = delicacyRepository;
        this.cocktailRepository = cocktailRepository;
        this.boothRepository = boothRepository;
    }

    @Override
    public String addDelicacy(String type, String name, double price) {
        if (delicacyRepository.getByName(name) != null) {
            throw new IllegalArgumentException(String.format(FOOD_OR_DRINK_EXIST, type, name));
        }

        Delicacy delicacy;

        if (type.equals("Gingerbread")) {
            delicacy = new Gingerbread(name, price);
        } else if (type.equals("Stolen")) {
            delicacy = new Stolen(name, price);
        } else {
            throw new IllegalArgumentException("Invalid type " + type);
        }


        delicacyRepository.add(delicacy);

        return String.format(DELICACY_ADDED, name, type);
    }

    @Override
    public String addCocktail(String type, String name, int size, String brand) {
        if (cocktailRepository.getByName(name) != null) {
            throw new IllegalArgumentException(String.format(FOOD_OR_DRINK_EXIST, type, name));
        }

        Cocktail cocktail;

        if (type.equals("Hibernation")) {
            cocktail = new Hibernation(name, size, brand);
        } else if (type.equals("MulledWine")) {
            cocktail = new MulledWine(name, size, brand);
        } else {
            throw new IllegalArgumentException("Invalid type " + type);
        }

        cocktailRepository.add(cocktail);

        return String.format(COCKTAIL_ADDED, name, brand);
    }

    @Override
    public String addBooth(String type, int boothNumber, int capacity) {
        if (boothRepository.getByNumber(boothNumber) != null) {
            throw new IllegalArgumentException(String.format(BOOTH_EXIST, boothNumber));
        }

        Booth booth;

        if (type.equals("OpenBooth")) {
            booth = new OpenBooth(boothNumber, capacity);
        } else if (type.equals("PrivateBooth")) {
            booth = new PrivateBooth(boothNumber, capacity);
        } else {
            throw new IllegalArgumentException("Invalid type " + type);
        }

        boothRepository.add(booth);

        return String.format(BOOTH_ADDED, boothNumber);
    }

    @Override
    public String reserveBooth(int numberOfPeople) {
        Booth suitableBooth = boothRepository.getAll().stream()
                .filter(b -> !b.isReserved() && b.getCapacity() >= numberOfPeople)
                .findFirst()
                .orElse(null);

        if (suitableBooth == null) {
            return String.format(RESERVATION_NOT_POSSIBLE, numberOfPeople);
        }

        suitableBooth.reserve(numberOfPeople);

        return String.format(BOOTH_RESERVED, suitableBooth.getBoothNumber(), numberOfPeople);
    }

    @Override
    public String leaveBooth(int boothNumber) {
        Booth booth = boothRepository.getByNumber(boothNumber);

        double bill = booth.getBill() + booth.getPrice();

        booth.clear();

        totalStoreIncome += bill;

        return String.format(BILL, boothNumber, bill);
    }

    @Override
    public String getIncome() {
        return String.format(TOTAL_INCOME, totalStoreIncome);
    }
}
