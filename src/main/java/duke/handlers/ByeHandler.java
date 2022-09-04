package duke.handlers;

import static duke.services.Ui.dukePrint;

public class ByeHandler {
    /**
     * Handles the BYE command.
     * Exits the program.
     */
    public static void handle() {
        dukePrint("Bye! Hope to see you again soon my highness!");
        System.exit(0);
    }
}
