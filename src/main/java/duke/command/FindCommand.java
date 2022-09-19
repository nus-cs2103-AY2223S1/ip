package duke.command;

import java.util.List;

import duke.storage.Storage;
import duke.task.TaskList;

/**
 * Command to search the list of tasks for a specified keyword.
 */
public class FindCommand extends Command {
    public static final String COMMAND_WORD = "find";

    private final String keyword;

    /**
     * Creates a Command to search the list of tasks for a keyword.
     *
     * @param keyword Word to use when searching.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword.trim();
    }

    /**
     * Searches the list of tasks for tasks that contain the keyword.
     *
     * @param tasks List of tasks.
     * @param storage Storage for the task list.
     * @return A String that lists the matching tasks.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        List<Integer> matches = tasks.search(keyword);
        StringBuilder sb = new StringBuilder("Here are the matching tasks in your list:");
        for (int i : matches) {
            sb.append("\n\t").append(i).append(".").append(tasks.getTask(i));
        }
        return sb.toString();
    }
}
