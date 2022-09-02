package duke;

/**
 * Represents a command to delete the task with this taskNo in the current tasklist.
 */
public class DeleteCommand extends Command {

    private final int taskNo;
    private static boolean isExit;

    DeleteCommand(int taskNo) {
        this.taskNo = taskNo;
        this.isExit = false;
    }

    /**
     * Execute the delete command.
     * @param tasks current tasklist.
     * @param ui .
     * @param storage .
     * @return String : the response of the duke.
     */
    String execute(TaskList tasks, Ui ui, Storage storage) {
        String[] taskInfo = tasks.delete(this.taskNo);
        String response = ui.deleted(taskInfo[0], Integer.parseInt(taskInfo[1]));
        return response;
    }

}
