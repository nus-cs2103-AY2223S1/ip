package anya;

/**
 * Represents a command to mark the task with this taskNo as done in the current tasklist.
 */
public class MarkCommand extends Command {

    private final int taskNo;

    MarkCommand(int taskNo) {
        this.taskNo = taskNo;
    }

    /**
     * Execute the mark command.
     * @param tasks current tasklist.
     * @param ui interaction class.
     * @return the response of Anya.
     * @throws AnyaException.
     */
    String execute(TaskList tasks, Ui ui) throws AnyaException {
        String modifiedTask = tasks.mark(this.taskNo);
        String response = ui.marked(modifiedTask);
        return response;
    }

}
