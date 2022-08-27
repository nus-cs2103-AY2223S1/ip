package blob.commands;

import blob.common.Messages;
import blob.tasks.Task;

public class MarkCommand extends TaskCommand {

    /** The index of the task in the task list to be marked as done */
    private int index;

    /**
     * Returns a command that when executed will attempt to mark a task in the task list as done.
     *
     * @param index The index of the task in the task list to be marked as done.
     */
    public MarkCommand(int index) {
        super("mark");
        this.index = index;
    }

    /**
     * {@inheritDoc}
     */
    public CommandResult execute() {
        try {
            Task task = taskList.getTask(index);
            task.markAsDone();
            return new CommandResult(Messages.MESSAGE_TASK_MARKED,
                    String.format("\n\t\t%s \n", task));
        } catch (IndexOutOfBoundsException exception) {
            return new CommandResult(Messages.MESSAGE_ERROR_TASK_NOT_FOUND);
        }
    }
}
