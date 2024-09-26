package duke;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    /**
     * Starts the Launcher
     *
     * @param args
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
