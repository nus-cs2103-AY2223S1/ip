package alpha;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {

    /**
     * Entry point of the program.
     * @param args User input.
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
