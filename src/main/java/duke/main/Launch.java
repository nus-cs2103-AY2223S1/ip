package duke.main;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launch {
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
