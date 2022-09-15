package duke.ui;

/**
 * Interface for the application.
 *
 */
public class Ui {

    private static Ui ui;

    private static final String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String welcomeMessage = "Hello! What can I do for you?";

    private static final String helpMessage = "Here are the commands you can use: \n"
            + "'todo (description)'\n"
            + "'deadline (description) /by (deadline date*)'\n"
            + "'event (description) /at (event date*)'\n"
            + "'find (filter)'\n"
            + "'unmark (task number)'\n"
            + "'mark (task number)'\n"
            + "'delete (task number)'\n"
            + "'list'\n"
            + "(*Please input your dates in yyyy-MM-dd HHmm format.)\n";

    /**
     * Prints out the help message.
     */
    public void getHelpMessage() {
        System.out.println(helpMessage);
    }

    /**
     * Prints out welcome message.
     */
    public void bootUpDuke() {
        System.out.println(welcomeMessage);
    }

    /**
     * Returns Ui instance if created, else create one.
     * Ensures Ui will only ever have one created object.
     *
     * @return Ui object.
     */
    public static Ui getInstance() {
        if (ui == null) {
            ui = new Ui();
        }
        return ui;
    }

    public String getWelcomeMessage() {
        return welcomeMessage;
    }

    /**
     * Prints out exit message.
     */
    public void shutDownDuke() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}
