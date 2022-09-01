package duke;

/**
 * Represents a command to add the task in the current tasklist.
 */
public class AddCommand extends Command {

    private final Task task;

    AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Execute the add command.
     * @param tasks current tasklist.
     * @param ui .
     * @param storage .
     * @return boolean false (true if exit and false if not exit).
     */
    boolean execute(TaskList tasks, Ui ui, Storage storage) {
        int noOfTasks = tasks.add(this.task);
        ui.added(this.task.toString(), noOfTasks);
        return false;
    }

}
