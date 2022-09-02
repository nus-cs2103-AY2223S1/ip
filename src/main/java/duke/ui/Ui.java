package duke.ui;

/**
 * Handles the inputs and outputs shown to the user.
 */
public class Ui {
    /**
     * Prints Duke's greeting to the user.
     */
    public String printGreeting() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        return "Hello from\n" + logo;
    }

    /**
     * Prints Duke's goodbye to the user.
     */
    public String printBye() {
        return "Goodbye. Hope to see you again soon!";
    }
}
