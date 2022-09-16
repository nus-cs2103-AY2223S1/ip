package blob.commands;

import blob.common.Messages;
import blob.tasks.Task;

/**
 * The DeleteCommand class represents the command to delete a task.
 */
public class DeleteCommand extends TaskCommand {

    /** The index of the task in the task list to be deleted */
    private int index;

    /**
     * Returns a command that when executed will attempt to delete a task from the task list.
     *
     * @param index The index of the task in the task list to be deleted.
     */
    public DeleteCommand(int index) {
        super("delete");
        this.index = index;
    }

    /**
     * {@inheritDoc}
     */
    public CommandResult execute() {
        try {
            Task task = taskList.getTask(index);
            taskList.deleteTask(index);
            return new CommandResult(Messages.MESSAGE_TASK_DELETED,
                    String.format("\n\t\t%s \n", task));
        } catch (IndexOutOfBoundsException e) {
            return new CommandResult(Messages.MESSAGE_ERROR_TASK_NOT_FOUND);
        }
    }
}
