package duke;

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
     * @param ui.
     * @return the response of the duke.
     */
    String execute(TaskList tasks, Ui ui) {
        String modifiedTask = tasks.mark(this.taskNo);
        String response = ui.marked(modifiedTask);
        return response;
    }

}
