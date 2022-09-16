package duke.handlers;

import static duke.services.Ui.dukePrint;

public class ByeHandler {
    /**
     * Handles the BYE Duke command.
     * Quits the running Duke instance.
     **/
    public static String getResponse(){
        return("Bye. Duke doesn't miss you.");
    }
}
