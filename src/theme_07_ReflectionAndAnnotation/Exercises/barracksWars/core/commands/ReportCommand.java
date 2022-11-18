package theme_07_ReflectionAndAnnotation.Exercises.barracksWars.core.commands;

import theme_07_ReflectionAndAnnotation.Exercises.barracksWars.annotations.Inject;
import theme_07_ReflectionAndAnnotation.Exercises.barracksWars.interfaces.Repository;

public class ReportCommand extends Command {
    @Inject
    private Repository repository;

    public ReportCommand(String[] data) {
        super(data);
    }

    @Override
    public String execute() {
        return this.repository.getStatistics();
    }

    public Repository getRepository() {
        return this.repository;
    }
}
