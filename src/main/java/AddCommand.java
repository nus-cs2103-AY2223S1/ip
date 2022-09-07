package duke;

/**
 * Represents a command to add the task in the current tasklist.
 */
public class AddCommand extends Command {

    private final Task task;
    private static boolean isExit;

    AddCommand(Task task) {
        this.task = task;
        this.isExit = false;
    }

    /**
     * Execute the add command.
     * @param tasks current tasklist.
     * @param ui .
     * @param storage .
     * @return String : the response of the duke.
     */
    String execute(TaskList tasks, Ui ui, Storage storage) {
        int noOfTasks = tasks.add(this.task);
        String response = ui.added(this.task.toString(), noOfTasks);
        assert this.isExit == false : "IsExit should be false";
        return response;
    }

}
