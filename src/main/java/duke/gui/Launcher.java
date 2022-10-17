package duke.gui;

import duke.Duke;
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
        if (args.length > 0 && args[0].equals("console")) {
            Duke.main(new String[]{});
            return;
        }
        Application.launch(Main.class, args);
    }
}

//@@author
