package roger;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    /**
     * Main class to launch Roger.
     *
     * @param args Ignored arguments.
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
        System.exit(0);
    }
}
