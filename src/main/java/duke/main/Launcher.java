package duke.main;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    /**
     * Main method of the launcher class.
     * Launches the application through the Main class.
     *
     * @param args User command line input.
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
