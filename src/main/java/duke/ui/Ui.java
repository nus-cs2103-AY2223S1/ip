package duke.ui;

/**
 * Interface for the application.
 *
 */
public class Ui {


    private static String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static String welcomeMessage = "Hello! What can I do for you?";

    /**
     * Prints out welcome message
     */
    public void bootUpDuke() {
        System.out.println(welcomeMessage);
    }

    public String getWelcomeMessage() {
        return welcomeMessage;
    }

    /**
     * Prints out exit message
     */
    public void shutDownDuke() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}
