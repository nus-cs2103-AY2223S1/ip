package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Command to print a task list.
 *
 * @author dexter-sim
 * @version 0.1
 */
public class ListCommand extends Command {
    /**
     * Executes the command to print the specified list of tasks.
     *
     * @param storage Storage handling the file IO.
     * @param taskList A list of tasks.
     * @param ui A ui to handle printing output.
     * @return A string from the result of execution.
     */
    @Override
    public String execute(Storage storage, TaskList taskList, Ui ui) {
        return ui.printList(taskList.toString());
    }
}
