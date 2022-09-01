package duke.command;

import duke.FileStorage;
import duke.Ui;
import duke.task.TaskList;

/**
 * Command used to list all tasks in the taskList.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    /**
     * Lists all tasks from the taskList
     * and returns the corresponding message to the GUI.
     *
     * @param list The taskList of Duke.
     * @param storage The fileStorage of Duke.
     * @param ui The Ui of Duke.
     * @return The message meant for the GUI.
     */
    @Override
    public String execute(TaskList list, FileStorage storage, Ui ui) {
        return ui.getActiveTasksMessage(list);
    }
}
