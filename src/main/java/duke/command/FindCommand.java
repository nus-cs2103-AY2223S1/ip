package duke.command;

import java.util.ArrayList;

import duke.FileStorage;
import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Command used to query tasks that matches the keywords in the taskList.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";
    private String[] queries;
    public FindCommand(String ... queries) {
        this.queries = queries;
    }

    /**
     * Finds the matching tasks from the taskList
     * and returns the corresponding message to the GUI.
     *
     * @param list The taskList of Duke.
     * @param storage The fileStorage of Duke.
     * @param ui The Ui of Duke.
     * @return The message meant for the GUI.
     */
    @Override
    public String execute(TaskList list, FileStorage storage, Ui ui) {
        ArrayList<Task> foundTasks = list.findTasks(queries);
        return ui.getFoundTasksMessage(foundTasks);
    }
}
