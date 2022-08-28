package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * List command for Duke application.
 *
 * @author Farrel Dwireswara Salim
 */
public class ListCommand implements Command {
    /**
     * Constructs a new instance of ListCommand.
     */
    public ListCommand() {}

    /**
     * Executes the ListCommand.
     *
     * @param ui the Ui object to handle user interface.
     * @param storage the storage used by the ListCommand.
     * @param taskList the task list used by the ListCommand.
     */
    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList) {
        ui.printTaskList(taskList);
    }
}
