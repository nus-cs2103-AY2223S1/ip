package commands;

import java.util.List;

import data.Task;
import data.TaskList;
import storage.Storage;

/**
 * Command to search for tasks that match a substring.
 */
public class FindCommand extends Command {
    private final String searchString;

    public FindCommand(String searchString) {
        this.searchString = searchString;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) {
        StringBuilder resultStringBuilder = new StringBuilder();
        resultStringBuilder.append("Here are the matching tasks in your list:\n");

        List<Task> matchingTasks = tasks.findBySearchString(searchString);
        for (int i = 0; i < matchingTasks.size(); i++) {
            resultStringBuilder.append(i + 1).append(". ").append(matchingTasks.get(i)).append("\n");
        }
        return resultStringBuilder.toString();
    }
}
