package duke.gui;

import javafx.application.Application;

//@@author clarence-chew-reused
// Reused from this tutorial
// https://se-education.org/guides/tutorials/javaFx.html
// with minor modifications at most

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    /**
     * Launches the GUI application.
     *
     * @param args Ignored arguments.
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}

//@@author
