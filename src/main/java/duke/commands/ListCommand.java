package duke.commands;

import duke.gui.Ui;
import duke.storage.Storage;
import duke.task.TaskList;

/**
 * Represents a list command.
 */
public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";
    public static final String SHORTER_COMMAND_WORD = "l";

    /**
     * Lists the current tasks in the task list.
     *
     * @param taskList The list of tasks in Duke.
     * @param ui The TextUi class used to print message in Duke.
     * @param storage The storage used to save the tasks in the local file.
     * @return The string representation of the current list of tasks.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return ui.getListCommandMessage(taskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
