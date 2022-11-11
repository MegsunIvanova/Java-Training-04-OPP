package theme_05_Polymorphism.Exercises.P04_Word.word;

public class Initialization {

    public static CommandInterface buildCommandInterface(StringBuilder text) {
        CommandInterface command = new AdvancedCommandInterface(text);
        command.init();

        return command;
    }
}
