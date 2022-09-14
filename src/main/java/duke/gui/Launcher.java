package duke.gui;

import duke.MainWrapper;
import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    public static void main(String[] args) {
        Application.launch(MainWrapper.class, args);
    }
}
