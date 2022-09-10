package duke.commands;

import java.util.ArrayList;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.TextUi;

/**
 * Represents a find command.
 */
public class FindCommand extends Command {
    public static final String COMMAND_WORD = "find";
    private String query;

    public FindCommand(String query) {
        this.query = query;
    }

    /**
     * Prints all tasks that match with the query.
     *
     * @param taskList The list of tasks in Duke.
     * @param ui The TextUi class used to print message in Duke.
     * @param storage The storage used to save the tasks in the local file.
     */
    @Override
    public void execute(TaskList taskList, TextUi ui, Storage storage) {
        ArrayList<Task> foundTask = taskList.findTask(this.query);
        ui.showFindTaskMessage(foundTask);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
