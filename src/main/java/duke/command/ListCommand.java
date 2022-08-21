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
     * Lists all tasks from the taskList.
     *     and prints out the corresponding message to the user.
     * @param list The taskList of Duke.
     * @param storage The fileStorage of Duke.
     * @param ui The Ui of Duke.
     */
    @Override
    public void execute(TaskList list, FileStorage storage, Ui ui) {
        ui.printActiveTasks(list);
    }
}
