package blob.commands;

import blob.common.Messages;
import blob.tasks.Task;

public class UnmarkCommand extends TaskCommand {

    /** The index of the task in the task list to be marked as undone */
    int index;

    /**
     * Returns a command that when executed will attempt to mark a task in the task list as undone.
     *
     * @param index The index of the task in the task list to be marked as undone.
     */
    public UnmarkCommand(int index) {
        super("unmark");
        this.index = index;
    }

    /**
     * {@inheritDoc}
     */
    public CommandResult execute() {
        try {
            Task task = taskList.getTask(index);
            task.markAsUndone();
            return new CommandResult(Messages.MESSAGE_TASK_UNMARKED,
                    String.format("\n\t\t%s \n", task));
        } catch (IndexOutOfBoundsException exception) {
            return new CommandResult(Messages.MESSAGE_ERROR_TASK_NOT_FOUND);
        }
    }
}
