package duke.ui;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    /**
     * The main function which in charge of launching the application
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
