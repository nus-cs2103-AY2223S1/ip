package duke;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    /**
     * Runs the application.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
