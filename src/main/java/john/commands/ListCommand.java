package john.commands;

/**
 * Represents a command to list out all the tasks.
 */
public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";
    public static final String FORMAT = "list <dd/mm/yyyy | optional>";

    private final String listParams;

    /**
     * Constructor for a ListCommand.
     * @param listParams The parameters for a list command.
     */
    public ListCommand(String listParams) {
        this.listParams = listParams;
    }

    /**
     * Returns a string representation of all the tasks in the task list.
     * @return A string representation of the tasks in the task list.
     */
    @Override
    public String execute() {
        return tasklist.listTasks(listParams);
    }
}
