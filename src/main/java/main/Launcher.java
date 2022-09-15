package main;

import javafx.application.Application;

/**
 * A launcher class used to workaround classpath issues
 */
public class Launcher {
    public static void main(String[] args) {
        Application.launch(Duke.class, args);
    }
}
