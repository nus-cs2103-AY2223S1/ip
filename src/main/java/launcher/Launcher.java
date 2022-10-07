package launcher;

import bocil.Main;
import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    /**
     * Runs the application.
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
