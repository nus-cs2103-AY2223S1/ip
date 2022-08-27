/**
 * This class handles all commands related to listing tasks
 * and inherits from the Command class.
 */
package duke;

public class ListCommand extends Command {
    /**
     * Constructor for the ListCommand class.
     */
    public ListCommand() {

    }

    /**
     * {@inheritDoc}
     * Lists all the tasks in the tasklist.
     */
    @Override
    public TaskList execute(TaskList taskList, Ui ui, Storage storage) {
        ui.printTasks(taskList);
        return taskList;
    }

    /**
     * Returns false.
     *
     * @return false.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
