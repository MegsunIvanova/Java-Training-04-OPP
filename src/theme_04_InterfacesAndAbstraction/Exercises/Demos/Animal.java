package theme_04_InterfacesAndAbstraction.Exercises.Demos;

public interface Animal {

    void walk();

    default void breathe() {
        System.out.println("Animal breathe ...");
    }

}
