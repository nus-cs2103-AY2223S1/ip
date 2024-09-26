package duke;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues
 *
 * @author Elbert Benedict
 */
public class Launcher {
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
