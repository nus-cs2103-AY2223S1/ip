package john.commands;

/**
 * Represents a command to delete a specified task.
 */
public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";
    public static final String FORMAT = "delete <integer>";

    private final String deleteParams;

    /**
     * Constructor for a DeleteCommand.
     * @param deleteParams The position of the task to delete.
     */
    public DeleteCommand(String deleteParams) {
        this.deleteParams = deleteParams;
    }

    /**
     * Deletes the specified task from the task list.
     * @return A string representation of the task deleted.
     */
    @Override
    public String execute() {
        return tasklist.deleteTask(this.deleteParams);
    }
}
