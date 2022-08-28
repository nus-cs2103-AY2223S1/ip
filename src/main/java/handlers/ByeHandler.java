package handlers;

import static services.Ui.dukePrint;

public class ByeHandler {
    public static void handle(){
        dukePrint("Bye. Duke doesn't miss you.");
        System.exit(0);
    }
}
