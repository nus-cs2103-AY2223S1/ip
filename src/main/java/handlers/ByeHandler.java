package handlers;

import static services.Ui.dukePrint;

public class ByeHandler {
    public static void handle() {
        dukePrint("Bye! Hope to see you again soon my highness!");
        System.exit(0);
    }
}
