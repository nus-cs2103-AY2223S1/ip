package duke;

/**
 * Represents a command to delete the task with this taskNo in the current tasklist.
 */
public class DeleteCommand extends Command {

    private final int taskNo;

    DeleteCommand(int taskNo) {
        this.taskNo = taskNo;
    }

    /**
     * Execute the delete command.
     * @param tasks current tasklist.
     * @param ui .
     * @param storage .
     * @return boolean false (true if exit and false if not exit).
     */
    boolean execute(TaskList tasks, Ui ui, Storage storage) {
        String[] taskInfo = tasks.delete(this.taskNo);
        ui.deleted(taskInfo[0], Integer.parseInt(taskInfo[1]));
        return false;
    }

}
