package duke;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class AhDukeLauncher {
    public static void main(String[] args) {
        Application.launch(AhDukeApp.class, args);
    }
}
