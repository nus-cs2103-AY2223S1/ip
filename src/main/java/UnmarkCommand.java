package duke;

/**
 * Represents a command to unmark the task with this taskNo as undone in the current tasklist.
 */
public class UnmarkCommand extends Command {

    private final int taskNo;

    UnmarkCommand(int taskNo) {
        this.taskNo = taskNo;
    }

    /**
     * Execute the unmark command.
     * @param tasks current tasklist.
     * @param ui .
     * @param storage .
     * @return boolean false (true if exit and false if not exit).
     */
    boolean execute(TaskList tasks, Ui ui, Storage storage) {
        String modifiedTask = tasks.unmark(this.taskNo);
        ui.unmarked(modifiedTask);
        return false;
    }

}
