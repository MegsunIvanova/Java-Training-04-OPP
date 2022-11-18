package theme_07_ReflectionAndAnnotation.Exercises.barracksWars;

import theme_07_ReflectionAndAnnotation.Exercises.barracksWars.core.commands.CommandInterpreterImpl;
import theme_07_ReflectionAndAnnotation.Exercises.barracksWars.interfaces.CommandInterpreter;
import theme_07_ReflectionAndAnnotation.Exercises.barracksWars.interfaces.Repository;
import theme_07_ReflectionAndAnnotation.Exercises.barracksWars.interfaces.Runnable;
import theme_07_ReflectionAndAnnotation.Exercises.barracksWars.interfaces.UnitFactory;
import theme_07_ReflectionAndAnnotation.Exercises.barracksWars.core.Engine;
import theme_07_ReflectionAndAnnotation.Exercises.barracksWars.core.factories.UnitFactoryImpl;
import theme_07_ReflectionAndAnnotation.Exercises.barracksWars.data.UnitRepository;

public class Main {

    public static void main(String[] args) {
        Repository repository = new UnitRepository();
        UnitFactory unitFactory = new UnitFactoryImpl();
        CommandInterpreter commandInterpreter = new CommandInterpreterImpl(repository, unitFactory);

        Runnable engine = new Engine(commandInterpreter);
        engine.run();
    }
}
