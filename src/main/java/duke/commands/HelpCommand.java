package duke.commands;

/**
 * Handler for help command.
 */
public class HelpCommand {
    private static String helpMessage = "Here are the commands you can use: \n"
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
     * Prints out help message for user.
     */
    public void executeCommand() {
        System.out.println(helpMessage);
    }
}
