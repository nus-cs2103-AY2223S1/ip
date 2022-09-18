package duke;

import duke.ui.Gui;
import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    public static void main(String[] args) {
        Application.launch(Gui.class, args);
    }
}
