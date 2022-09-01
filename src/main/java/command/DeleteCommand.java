package command;

/**
 * Responsible for deletion of tasks from the task list.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";
    private static final String MESSAGE_SUCCESS = "I'VE DELETED THIS TASK:\n: %1$s.";
    private final int index;

    /**
     * Creates a new DeleteCommand with the given index.
     * @param index the index of the task (0-indexed) to be deleted from
     *              the taskList.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public CommandResult execute() {
        return new CommandResult(String.format(MESSAGE_SUCCESS, taskList.deleteTask(index)), taskList);
    }
}
