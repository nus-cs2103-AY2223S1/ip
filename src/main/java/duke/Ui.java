package duke;

public class Ui {
    /**
     * Application logo
     */
    private static final String LOGO =
            " ____        _        \n" +
            "|  _ \\ _   _| | _____ \n" +
            "| | | | | | | |/ / _ \\\n" +
            "| |_| | |_| |   <  __/\n" +
            "|____/ \\__,_|_|\\_\\___|\n";

    /**
     * Available commands
     */
    private static final String AVAILABLE_COMMANDS =
            "Available commands:\n" +
                    "   deadline [TASK DESCRIPTION] /by [DUE DATE]\n" +
                    "   event    [TASK DESCRIPTION] /at [VENUE]\n" +
                    "   todo     [TASK DESCRIPTION]\n" +
                    "   delete   [TASK NUMBER]\n" +
                    "   mark     [TASK NUMBER]\n" +
                    "   unmark   [TASK NUMBER]\n" +
                    "   list\n" +
                    "   find\n" +
                    "   bye\n";

    /**
     * Constructor for `Ui` object.
     */
    public Ui() {
        System.out.println("Hello from\n" +
                LOGO +
                AVAILABLE_COMMANDS);
    }

    /**
     * Method to display available commands to users.
     */
    public void showAvailableCommands() {
        System.out.print(AVAILABLE_COMMANDS);
    }

    /**
     * Show given String to user.
     */
    public void showUser(String string) {
        System.out.println(string);
    }

}
