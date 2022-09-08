package duke.frontend;

import duke.Main;
import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    /**
     * Main method to launch the program.
     * @param args args to pass in Main
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
