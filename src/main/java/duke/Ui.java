package duke;

import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Class to deal with user interface
 */
public class Ui {
    private static final String BYE = "Bye! Hope to see you again soon!";
    private Scanner sc;

    /**
     * Constructor for UI
     */
    Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Exits the programme
     * @return String as user response
     */
    public String sayBye() {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                System.exit(0);
            }
        }, 1000);
        return BYE;
    }


}
