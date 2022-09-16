package duke.ui;

/**
 * Interface for the application.
 *
 */
public class Ui {

    private static Ui ui;

    /** Welcome message */
    private static final String welcomeMessage = "Hello! What can I do for you?";

    /** Help message */
    private static final String helpMessage = "Here are the commands you can use: \n"
            + "'todo (description)'\n"
            + "'deadline (description) /by (date*)'\n"
            + "'event (description) /at (date*)'\n"
            + "'find (filter)'\n"
            + "'unmark (task number)'\n"
            + "'mark (task number)'\n"
            + "'delete (task number)'\n"
            + "'list'\n"
            + "(*Dates should in yyyy-MM-dd HHmm format )\n";

    /**
     * Prints out the help message.
     */
    public void getHelpMessage() {
        System.out.println(helpMessage);
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

    /**
     *  Returns Duke's welcome message.
     */
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
