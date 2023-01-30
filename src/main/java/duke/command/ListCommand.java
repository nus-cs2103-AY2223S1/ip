package duke.command;

import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * A class to represent a command to list all tasks in the task list.
 */
public class ListCommand extends Command {

    /**
     * Constructs a ListCommand object.
     *
     * @param storage  Storage class to be used
     * @param ui       Ui class to be used
     * @param taskList TaskList to be used
     */
    public ListCommand(Storage storage, Ui ui, TaskList taskList) {
        super(storage, ui, taskList);
    }

    /**
     * Lists all tasks in the task list.
     *
     * @return String representation of all tasks in the task list
     */
    @Override
    public String execute() {
        return taskList.displayList();
    }
}
