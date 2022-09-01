package caca;

import javafx.application.Application;

//@@author carriezhengjr-reused
// Reused from https://se-education.org/guides/tutorials/javaFxPart4.html

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {

    /**
     * Launches the application.
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
