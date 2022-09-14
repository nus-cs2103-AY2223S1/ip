package duke.gui;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {

    /**
     * Launches the main class of the application.
     *
     * @param args Arguments to launch the main class.
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
