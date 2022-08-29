package duke;

/**
 * Handles some print statements from Duke.
 *
 */
public class Ui {

    /** Constructor for a Ui object */
    protected Ui() {

    }

    /**
     * Displays Duke logo and opening statements when starting Duke.
     *
     */
    protected void displayLogo() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
    }
}
