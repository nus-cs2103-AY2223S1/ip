package duke.command;

/**
 * ExitCommand represents a command to exit the application.
 */
public class ExitCommand extends Command {
    private static final String GOODBYE = "Bye. Hope to see you again soon!" + "\n";

    /**
     * Displays the goodbye message.
     * @return The message to be displayed.
     */
    @Override
    public String action() {
        return GOODBYE;
    }
}
