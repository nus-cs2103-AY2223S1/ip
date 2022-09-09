package duke;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    public static void main(String[] args) {

        // calling launch method sends String array of arguments into launch method
        // invokes start method
        Application.launch(Main.class, args);
    }
}