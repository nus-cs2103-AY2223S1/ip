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
     * @param ui .
     * @param storage .
     * @return boolean false (true if exit and false if not exit).
     */
    boolean execute(TaskList tasks, Ui ui, Storage storage) {
        String modifiedTask = tasks.mark(this.taskNo);
        ui.marked(modifiedTask);
        return false;
    }

}
