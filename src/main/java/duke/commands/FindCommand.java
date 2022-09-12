package duke.commands;

import java.util.List;
import duke.gui.Ui;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;

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
     * @return The string representation of the list of matching tasks.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        List<Task> foundTask = taskList.findTask(this.query);
        return ui.getFindCommandMessage(foundTask);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
