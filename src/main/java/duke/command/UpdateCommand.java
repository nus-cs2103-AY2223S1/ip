package duke.command;

import duke.task.Task;

/**
 * A command that updates a task in the task list.
 */
public abstract class UpdateCommand extends Command {
    protected Task task;
    protected int taskIndex;

    /**
     * Constructor for UpdateCommand.
     *
     * @param command input string from user.
     * @param task Task to be updated.
     * @param taskIndex 0-based index of task to be updated in task list.
     */
    public UpdateCommand(String command, Task task, int taskIndex) {
        super(command);
        this.task = task;
        this.taskIndex = taskIndex;
    }
}
