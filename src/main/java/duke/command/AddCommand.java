package duke.command;

import duke.task.Task;
import duke.task.TaskList;

/**
 * AddCommand class to add task to tasklist.
 */
public class AddCommand extends Command{
    private final Task task;

    /**
     * Constructor for AddCommand.
     *
     * @param task The task to be added.
     */
    public AddCommand (Task task) {
        this.task = task;
    }

    /**
     * Adds task into the lastlist.
     *
     * @param tasks The tasks to be executed.
     */
    @Override
    public void execute(TaskList tasks) {
        tasks.addTask(this.task);
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
