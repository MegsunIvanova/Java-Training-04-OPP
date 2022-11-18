package theme_07_ReflectionAndAnnotation.Exercises.barracksWars.core.commands;

import theme_07_ReflectionAndAnnotation.Exercises.barracksWars.interfaces.Executable;

public abstract class Command implements Executable {

    private String[] data;


    public Command(String[] data) {
        this.data = data;
    }

    public String[] getData() {
        return data;
    }

}
