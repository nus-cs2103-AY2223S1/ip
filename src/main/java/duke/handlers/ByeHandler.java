package duke.handlers;

import static duke.services.Ui.dukePrint;

public class ByeHandler {
    /**
     * Handles the bye Duke command.
     * Quits the running Duke instance.
     **/
    public static void handle(){
        dukePrint("Bye. duke.Duke doesn't miss you.");
        System.exit(0);
    }
}
