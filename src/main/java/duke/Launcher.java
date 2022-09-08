package duke;

import duke.ui.GUI;
import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 *
 * @author Tan Jun Wei
 */
public class Launcher {
    public static void main(String[] args) {
        Application.launch(GUI.class, args);
    }
}