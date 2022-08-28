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

    public FindCommand(String keyword) {
        this.keyword = keyword.trim();
    }

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
