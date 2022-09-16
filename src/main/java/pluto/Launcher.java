package pluto;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    /**
     * Provides the entry point for application.
     * @param args Command Line arguments.
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
