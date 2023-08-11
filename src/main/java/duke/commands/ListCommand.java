package duke.commands;

import duke.data.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Represents the command to list all the tasks.
 */
public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";

    /**
     * Returns a message indicating all tasks on the list to be shown to the user.
     * @param taskList List of tasks.
     * @param ui Shows all the tasks on the list to the user.
     * @param storage Saves the modified list of tasks.
     * @return A message indicating all tasks on the list.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return ui.showAllTasks(taskList);
    }
}
