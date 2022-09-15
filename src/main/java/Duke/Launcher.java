package duke;

import javafx.application.Application;

/**
 * A duke.Launcher class to workaround classpath issues.
 */
public class Launcher {
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }

}
