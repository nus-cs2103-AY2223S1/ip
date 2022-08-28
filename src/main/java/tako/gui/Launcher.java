package tako.gui;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    /**
     * Launches the Main class.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
