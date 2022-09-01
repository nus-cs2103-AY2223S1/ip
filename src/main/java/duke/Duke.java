package duke;

import duke.gui.Gui;
import duke.tools.SessionManager;
import javafx.application.Application;

/**
 * Duke is an interactive robot that helps to keep track of your tasks.
 */
public class Duke {
    /**
     * This is the main method where the program runs from.
     *
     * @param args Unused parameter
     */
    public static void main(String ... args) {
        SessionManager.startSession();
        Application.launch(Gui.class, args);
    }
}
