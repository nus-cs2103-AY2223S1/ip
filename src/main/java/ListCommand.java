package duke;

/**
 * Represents a command to list all the task in the current tasklist.
 */
public class ListCommand extends Command {

    private static boolean isExit;

    ListCommand() {
        this.isExit = false;
    }

    /**
     * Execute the list command.
     * @param tasks current tasklist.
     * @param ui .
     * @param storage .
     * @return String : the response of the duke.
     */
    String execute(TaskList tasks, Ui ui, Storage storage) {
        String response = ui.printList(tasks.getList());
        assert this.isExit == false : "IsExit should be false";
        return response;
    }

}
