package theme_07_ReflectionAndAnnotation.Exercises.barracksWars.interfaces;

public interface CommandInterpreter {

	Executable interpretCommand(String[] data, String commandName);
}
