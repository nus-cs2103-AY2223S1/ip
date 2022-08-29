package duke.command;

import java.util.ArrayList;

import duke.FileStorage;
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
     */
    @Override
    public String execute(TaskList list, FileStorage storage) {
        ArrayList<Task> foundTasks = list.findTasks(queries);
        StringBuilder strBuilder = new StringBuilder("Here are the matching tasks in your list:");
        for (int i = 0; i < foundTasks.size(); i++) {
            strBuilder.append("\n").append(i + 1).append(".").append(foundTasks.get(i));
        }
        return strBuilder.toString();
    }
}
