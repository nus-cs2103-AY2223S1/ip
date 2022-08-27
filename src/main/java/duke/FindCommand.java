/**
 * This class handles the find command and extends from Command class.
 */
package duke;

public class FindCommand extends Command {

    private String description;

    /**
     * Constructor for the FindCommand class.
     *
     * @param description String to be found.
     */
    public FindCommand(String description) {
        this.description = description;
    }

    /**
     * {@inheritDoc}
     * Finds tasks in the tasklist.
     */
    @Override
    public TaskList execute(TaskList taskList, Ui ui, Storage storage) {
        TaskList tempList = taskList.findTasks(this.description);
        ui.showMatchingTasks(tempList);
        return taskList;
    }

    /**
     * {@inheritDoc}
     * Returns false.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
