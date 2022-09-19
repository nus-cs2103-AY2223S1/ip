package manmeowth;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    /**
     * Provides entry point into ManMeowth program using GUI.
     *
     * @param args The command line arguments.
     **/
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
