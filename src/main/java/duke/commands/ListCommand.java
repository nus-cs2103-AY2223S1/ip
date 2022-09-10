package duke.commands;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.TextUi;

/**
 * Represents a list command.
 */
public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";

    /**
     * Lists the current tasks in the task list.
     *
     * @param taskList The list of tasks in Duke.
     * @param ui The TextUi class used to print message in Duke.
     * @param storage The storage used to save the tasks in the local file.
     */
    @Override
    public void execute(TaskList taskList, TextUi ui, Storage storage) {
        ui.showListTaskMessage(taskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
