package duke;

/**
 * Represents a command to unmark the task with this taskNo as undone in the current tasklist.
 */
public class UnmarkCommand extends Command {

    private final int taskNo;
    private static boolean isExit;

    UnmarkCommand(int taskNo) {
        this.taskNo = taskNo;
        this.isExit = false;
    }

    /**
     * Execute the unmark command.
     * @param tasks current tasklist.
     * @param ui .
     * @param storage .
     * @return String : the response of the duke.
     */
    String execute(TaskList tasks, Ui ui, Storage storage) {
        String modifiedTask = tasks.unmark(this.taskNo);
        String response = ui.unmarked(modifiedTask);
        assert this.isExit == false : "IsExit should be false";
        return response;
    }

}
