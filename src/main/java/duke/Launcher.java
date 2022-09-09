package duke;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    // @@author jorrdansoh-reused
    // Reused from https://se-education.org/guides/tutorials/javaFx.html
    public static void main(String[] args) {
        Application.launch(duke.Main.class, args);
    }
    // @@author
}
