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
     * @param ui.
     * @return the response of the duke.
     */
    String execute(TaskList tasks, Ui ui) {
        String modifiedTask = tasks.unmark(this.taskNo);
        String response = ui.unmarked(modifiedTask);
        return response;
    }

}
