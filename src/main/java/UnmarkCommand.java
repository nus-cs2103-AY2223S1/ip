package anya;

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
     * @param ui interaction class.
     * @return the response of Anya.
     * @throws AnyaException.
     */
    String execute(TaskList tasks, Ui ui) throws AnyaException {
        String modifiedTask = tasks.unmark(this.taskNo);
        String response = ui.unmarked(modifiedTask);
        return response;
    }

}
