package theme_07_ReflectionAndAnnotation.Exercises.barracksWars.core.commands;

import theme_07_ReflectionAndAnnotation.Exercises.barracksWars.annotations.Inject;
import theme_07_ReflectionAndAnnotation.Exercises.barracksWars.interfaces.Repository;
import theme_07_ReflectionAndAnnotation.Exercises.barracksWars.interfaces.Unit;
import theme_07_ReflectionAndAnnotation.Exercises.barracksWars.interfaces.UnitFactory;

public class AddCommand extends Command {

    @Inject
    private Repository repository;

    @Inject
    private UnitFactory unitFactory;

    public AddCommand(String[] data) {
        super(data);
    }

    @Override
    public String execute() {
        String unitType = this.getData()[1];
        Unit unitToAdd = this.unitFactory.createUnit(unitType);
        this.repository.addUnit(unitToAdd);
        String output = unitType + " added!";
        return output;
    }

    public Repository getRepository() {
        return repository;
    }

    public UnitFactory getUnitFactory() {
        return unitFactory;
    }
}
