package duke;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {

    public static void launch(String[] args) {
        Application.launch(Main.class, args);
    }
}
