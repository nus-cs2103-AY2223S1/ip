package duke;

import javafx.application.Application;

/**
 * Represents the launcher for launching the Duke application.
 * Used as a workaround for classpath issues.
 */
public class Launcher {
    /**
     * Enters the Duke application.
     *
     * @param args Arguments for duke application.
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
