package duke;

import javafx.application.Application;

/**
 * A launcher for the Duke GUI application.
 */
public class DukeGuiLauncher {
    /**
     * The entry point for the graphical version of Duke.
     * @param args Command-line parameters.
     */
    public static void main(String[] args) {
        Application.launch(DukeGui.class, args);
    }
}
