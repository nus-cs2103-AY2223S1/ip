package duke.gui;

import javafx.application.Application;

/**
 * Encapsulates a launcher to workaround classpath issues.
 */
public class Launcher {
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
