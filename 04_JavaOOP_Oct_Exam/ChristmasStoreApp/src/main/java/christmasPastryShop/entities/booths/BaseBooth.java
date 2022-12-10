package christmasPastryShop.entities.booths;

import christmasPastryShop.common.DataValidator;
import christmasPastryShop.entities.booths.interfaces.Booth;
import christmasPastryShop.entities.cocktails.interfaces.Cocktail;
import christmasPastryShop.entities.delicacies.interfaces.Delicacy;

import java.util.ArrayList;
import java.util.Collection;

import static christmasPastryShop.common.DataValidator.*;
import static christmasPastryShop.common.ExceptionMessages.*;

public abstract class BaseBooth implements Booth {
    private Collection<Delicacy> delicacyOrders;
    private Collection<Cocktail> cocktailOrders;
    private int boothNumber;
    private int capacity;
    private int numberOfPeople;
    private double pricePerPerson;
    private boolean isReserved;
    private double price;

    protected BaseBooth(int boothNumber, int capacity, double pricePerPerson) {
        this.delicacyOrders = new ArrayList<>();
        this.cocktailOrders = new ArrayList<>();

        this.boothNumber = boothNumber;
        setCapacity(capacity);
        this.pricePerPerson = pricePerPerson;
    }

    private void setCapacity(int capacity) {
        validateNumberIsNotLessOrEqualsToZero(capacity, INVALID_TABLE_CAPACITY);
        this.capacity = capacity;
    }

    private void setNumberOfPeople(int numberOfPeople) {
        validateNumberIsNotLessOrEqualsToZero(numberOfPeople, INVALID_NUMBER_OF_PEOPLE);
        this.numberOfPeople = numberOfPeople;
    }

    @Override
    public int getBoothNumber() {
        return this.boothNumber;
    }

    @Override
    public int getCapacity() {
        return this.capacity;
    }

    @Override
    public boolean isReserved() {
        return this.isReserved;
    }

    @Override
    public double getPrice() {
        return this.price;
    }

    @Override
    public void reserve(int numberOfPeople) {
        this.setNumberOfPeople(numberOfPeople);
        this.isReserved = true;
        this.price = this.pricePerPerson * numberOfPeople;
    }

    @Override
    public double getBill() {
        double sumOfCocktails = this.cocktailOrders.stream()
                .mapToDouble(Cocktail::getPrice)
                .sum();

        double sumOfDelicacy = this.delicacyOrders.stream()
                .mapToDouble(Delicacy::getPrice)
                .sum();

        double bill = sumOfCocktails + sumOfDelicacy;

        return bill;
    }

    @Override
    public void clear() {
        this.delicacyOrders.clear();
        this.cocktailOrders.clear();
        this.numberOfPeople = 0;
        this.isReserved = false;
        this.price = 0;
    }
}
