package duke.handlers;

import static duke.services.Ui.dukePrint;

public class ByeHandler {
    public static void handle(){
        dukePrint("Bye. duke.Duke doesn't miss you.");
        System.exit(0);
    }
}
