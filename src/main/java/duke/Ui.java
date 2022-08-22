package duke;

/**
 * Defines <Code>Ui</Code> class.
 * <p>This class is in charge of user interface operations. </p>
 */
public class Ui {
    /** <Code>String</Code> of application logo. */
    private static final String LOGO
            = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    /** <Code>String</Code> of all available commands and their format. */
    private static final String AVAILABLE_COMMANDS
            = "Available commands:\n"
            + "   deadline [TASK DESCRIPTION] /by [DUE DATE]\n"
            + "   event    [TASK DESCRIPTION] /at [VENUE]\n"
            + "   todo     [TASK DESCRIPTION]\n"
            + "   delete   [TASK NUMBER]\n"
            + "   mark     [TASK NUMBER]\n"
            + "   unmark   [TASK NUMBER]\n"
            + "   list\n"
            + "   find     [WORD TO SEARCH FOR]\n"
            + "   bye\n";

    /**
     * Constructor for <Code>Ui</Code>.
     */
    public Ui() {
        System.out.println("Hello from\n" + LOGO + AVAILABLE_COMMANDS);
    }

    /**
     * Displays available commands to users.
     */
    public void showAvailableCommands() {
        System.out.print(AVAILABLE_COMMANDS);
    }

    /**
     * Shows given String to user.
     */
    public void showUser(String string) {
        System.out.println(string);
    }
}
