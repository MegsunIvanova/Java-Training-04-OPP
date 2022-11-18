package theme_07_ReflectionAndAnnotation.Exercises.barracksWars.core.factories;

import theme_07_ReflectionAndAnnotation.Exercises.barracksWars.interfaces.Unit;
import theme_07_ReflectionAndAnnotation.Exercises.barracksWars.interfaces.UnitFactory;
import jdk.jshell.spi.ExecutionControl;
import theme_07_ReflectionAndAnnotation.Exercises.barracksWars.models.units.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class UnitFactoryImpl implements UnitFactory {

    private static final String UNITS_PACKAGE_NAME =
            "theme_07_ReflectionAndAnnotation.Exercises.barracksWars.models.units.";

    @Override
    public Unit createUnit(String unitType)  {
        try {
            Class unitClass = Class.forName(UNITS_PACKAGE_NAME + unitType);
            Constructor<Unit> constructor = unitClass.getDeclaredConstructor();
            return constructor.newInstance();
        } catch (ClassNotFoundException | InvocationTargetException | NoSuchMethodException | InstantiationException | IllegalAccessException e) {
            System.out.println(e.getMessage());
        }

        return null;

//        switch (unitType) {
//            case "Archer":
//                return new Archer();
//            case "Swordsman":
//                return new Swordsman();
//            case "Pikeman":
//                return new Pikeman();
//            case "Horseman":
//                return new Horseman();
//            case "Gunner":
//                return new Gunner();
//            default:
//                throw new ExecutionControl.NotImplementedException("message");
//        }

    }
}
