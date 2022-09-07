package duke;

/**
 * Represents a command to mark the task with this taskNo as done in the current tasklist.
 */
public class MarkCommand extends Command {

    private final int taskNo;
    private static boolean isExit;

    MarkCommand(int taskNo) {
        this.taskNo = taskNo;
        this.isExit = false;
    }

    /**
     * Execute the mark command.
     * @param tasks current tasklist.
     * @param ui .
     * @param storage .
     * @return String : the response of the duke.
     */
    String execute(TaskList tasks, Ui ui, Storage storage) {
        String modifiedTask = tasks.mark(this.taskNo);
        String response = ui.marked(modifiedTask);
        assert this.isExit == false : "IsExit should be false";
        return response;
    }

}
