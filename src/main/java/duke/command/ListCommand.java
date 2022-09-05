package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.UI;

/**
 * Representation of the list command.
 */
public class ListCommand extends Command {
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the ListCommand.
     * @param taskList TaskList of Tasks to be listed.
     * @param ui UI to print to users.
     * @param storage Storage to save and load TaskList.
     */
    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) {
        ui.printTaskList(taskList, "Here are the tasks in your list:");
    }
    /**
     * Returns message from the ListCommand.
     * @param taskList TaskList of Tasks to be listed.
     * @param ui UI to print to users.
     * @param storage Storage to save and load TaskList.
     */
    @Override
    public String getResponse(TaskList taskList, UI ui, Storage storage) {
        return ui.getTaskListResponse(taskList, "Here are the tasks in your list:");
    }
}
