package duke;

import duke.ui.Main;
import javafx.application.Application;

/**
 * A class that encapsulates the launching of the Duke
 * program.
 */
public class Launcher {

    /**
     * Drives the application
     * @param args Launch arguments.
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
