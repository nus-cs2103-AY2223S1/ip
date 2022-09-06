package command;

import exceptions.HenryException;

/**
 * Responsible for deletion of tasks from the task list.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";
    private static final String MESSAGE_SUCCESS = "I'VE DELETED THIS TASK:\n %1$s";
    private static final String MESSAGE_ERROR = "INDEX IS OUT OF RANGE! YOUR TASK LIST HAS %1$d ITEMS.";
    private final int index;

    /**
     * Creates a new DeleteCommand with the given index.
     *
     * @param givenIndex the index of the task (0-indexed) to be deleted from
     *                   the taskList.
     */
    public DeleteCommand(int givenIndex) {
        this.index = givenIndex;
    }

    @Override
    public CommandResult execute() {
        if (index > taskList.size() - 1) {
            throw new HenryException(String.format(MESSAGE_ERROR, taskList.size()));
        }
        return new CommandResult(String.format(MESSAGE_SUCCESS, taskList.deleteTask(index)), taskList);
    }
}
