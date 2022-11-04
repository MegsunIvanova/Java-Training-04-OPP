package theme_03_Inheritance.Exercises.P06_Animals;

public class Dog extends Animal {


    public Dog(String name, int age, String gender) {
        super(name, age, gender);
    }

    @Override
    public String produceSound() {
        return "Woof!";
    }

}
