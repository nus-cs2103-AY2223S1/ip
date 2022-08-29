package john.commands;

/**
 * Represents a command to unmark a specified task.
 */
public class UnmarkCommand extends Command {
    public static final String COMMAND_WORD = "unmark";
    public static final String FORMAT = "unmark <integer>";

    private final String unmarkParams;

    /**
     * Constructor for a UnmarkCommand.
     * @param unmarkParams The position of the task to unmark.
     */
    public UnmarkCommand(String unmarkParams) {
        this.unmarkParams = unmarkParams;
    }

    /**
     * Unmarks the specified task in the task list.
     * @return A string representation of the task unmarked.
     */
    @Override
    public String execute() {
        return tasklist.unmarkTask(this.unmarkParams);
    }
}
