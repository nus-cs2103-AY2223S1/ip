package stashy.launcher;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    /**
     * Launches the GUI application.
     *
     * @param args Command-line arguments
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
