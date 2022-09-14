package duke;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 * @author Jason
 */
public class Launcher {
    /**
     * Starts the application.
     */
    public static void main(String[] args) {
        assert(args != null);
        Application.launch(Main.class, args);
    }
}
