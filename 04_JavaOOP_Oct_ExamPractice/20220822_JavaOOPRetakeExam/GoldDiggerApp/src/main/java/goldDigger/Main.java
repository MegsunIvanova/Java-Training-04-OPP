package goldDigger;

import goldDigger.core.Controller;
import goldDigger.core.ControllerImpl;
import goldDigger.core.Engine;
import goldDigger.core.EngineImpl;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        Controller controller = new ControllerImpl();

        Engine engine = new EngineImpl(controller);

        engine.run();

    }

}
