package zoo.entities.areas;

import org.junit.Before;
import org.junit.Test;
import zoo.entities.animals.Animal;
import zoo.entities.animals.AquaticAnimal;
import zoo.entities.animals.TerrestrialAnimal;
import zoo.entities.foods.Food;
import zoo.entities.foods.Meat;
import zoo.entities.foods.Vegetable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BaseAreaTest {
    private static final String WATER_AREA_NAME = "The Lake";
    private static final String LAND_AREA_NAME = "The Cave";
    private static final int WATER_AREA_CAPACITY = 10;
    private static final int LAND_AREA_CAPACITY = 25;
    private static final String ANIMAL_NAME = "Test_Name";
    private static final String ANIMAL_KIND = "Test_Kind";
    private Area landArea;
    private Area waterArea;

    @Before
    public void setUp() {
        landArea = new LandArea(LAND_AREA_NAME);
        waterArea = new WaterArea(WATER_AREA_NAME);
    }


    @Test
    public void testConstructorOfWaterAreaShouldCreateWaterArea() {
        Area area = new WaterArea(WATER_AREA_NAME);
        assertEquals(WaterArea.class, area.getClass());
        assertEquals(WATER_AREA_NAME, area.getName());
    }

    @Test
    public void testConstructorOfWaterAreaShouldCreateLandArea() {
        Area area = new LandArea(LAND_AREA_NAME);
        assertEquals(LandArea.class, area.getClass());
        assertEquals(LAND_AREA_NAME, area.getName());
    }

    @Test(expected = NullPointerException.class)
    public void testConstructorOfAreaShouldThrowWithNullName() {
        Area area = new WaterArea(null);
    }

    @Test(expected = NullPointerException.class)
    public void testConstructorOfAreaShouldThrowWithWhitespaceName() {
        Area area = new LandArea(" ");
    }

    @Test
    public void testSumCaloriesShouldReturnCorrectSum() {
        List<Food> foodList = addFood(landArea);
        int expectedSum = foodList.stream()
                .mapToInt(Food::getCalories)
                .sum();
        int actualSum = landArea.sumCalories();
        assertEquals(expectedSum, actualSum);

    }

    @Test
    public void testSumCaloriesShouldReturnZeroIfFoodIsEmpty() {
        assertEquals(0, waterArea.sumCalories());
    }

    @Test
    public void testAddAnimalShouldAdd() {
        List<Animal> animals = addAnimals(landArea, LAND_AREA_CAPACITY);
        assertEquals(animals.size(), landArea.getAnimals().size());
    }

    @Test(expected = IllegalStateException.class)
    public void testAddAnimalShouldThrowExceptionIfFullCapacity() {
        List<Animal> animals = addAnimals(waterArea, WATER_AREA_CAPACITY + 1);
    }

    private List<Animal> addAnimals(Area area, int numberOfAnimals) {
        List<Animal> animalsList = new ArrayList<>();

        for (int i = 0; i < numberOfAnimals; i++) {
            String animalName = ANIMAL_NAME + " " + i;
            String animalKind = ANIMAL_KIND + " " + i;
            int animalPrice = (i + 10) * 11;

            Animal animal;
            if (i % 3 == 0) {
                animal = new AquaticAnimal(animalName, animalKind, animalPrice);
            } else {
                animal = new TerrestrialAnimal(animalName, animalKind, animalPrice);
            }
            animalsList.add(animal);
            area.addAnimal(animal);
        }

        return animalsList;
    }

    @Test
    public void testRemoveAnimalShouldRemoveExistingAnimal() {
        List<Animal> animals = addAnimals(landArea, LAND_AREA_CAPACITY);
        Animal animalForRemove = animals.get(animals.size() - 1);
        landArea.removeAnimal(animalForRemove);

        int expectedSize = animals.size() - 1;

        assertEquals(expectedSize, landArea.getAnimals().size());

        int index = 0;
        for (Animal animal : landArea.getAnimals()) {
            assertEquals(animals.get(index++), animal);
        }
    }

    @Test
    public void testRemoveAnimalNotRemoveNonExistingAnimal() {
        List<Animal> animals = addAnimals(waterArea, WATER_AREA_CAPACITY);
        waterArea.removeAnimal(new TerrestrialAnimal("Name", "Kind", 5));
        assertEquals(animals.size(), waterArea.getAnimals().size());
    }

    @Test
    public void testGetInfoOnEmptyCollectionOfAnimalsAndFoods() {
        String actual = waterArea.getInfo();

        StringBuilder expected = new StringBuilder();
        expected.append(String.format("%s (%s):", WATER_AREA_NAME, "WaterArea")).append(System.lineSeparator());

        expected.append("Animals: none").append(System.lineSeparator());
        expected.append("Foods: 0").append(System.lineSeparator());
        expected.append("Calories: 0");

        assertEquals(expected.toString(), actual);
    }

    @Test
    public void testAddFoodShouldAdd() {
        int expected = addFood(waterArea).size();
        int actual = waterArea.getFoods().size();
        assertEquals(expected, actual);
    }

    @Test
    public void testFeedShouldFeedAllAnimals() {
        landArea.addAnimal(new AquaticAnimal("Test1", "Test", 10));
        landArea.addAnimal(new TerrestrialAnimal("Test2", "Test", 10));

        landArea.feed();
        List<Animal> animals = new ArrayList<>();

        for (Animal animal : landArea.getAnimals()) {
            animals.add(animal);
        }

        assertEquals(2.50 + 7.50, animals.get(0).getKg(), 0.00);
        assertEquals(5.50 + 5.70, animals.get(1).getKg(), 0.00);

    }

    private List<Food> addFood(Area area) {
        List<Food> foodList = new ArrayList<>();

        for (int i = 0; i < 12; i++) {
            Food food;
            if (i % 3 == 0) {
                food = new Vegetable();
            } else {
                food = new Meat();
            }

            foodList.add(food);
            area.addFood(food);
        }

        return foodList;
    }


}