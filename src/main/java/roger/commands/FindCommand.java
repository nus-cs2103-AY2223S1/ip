package roger.commands;

import java.util.List;

import roger.storage.Storage;
import roger.tasks.Task;
import roger.tasks.TaskList;


/**
 * Encapsulates the command to delete a task.
 */
public class FindCommand extends Command {
    protected String query;

    /**
     * Create a FindCommand.
     *
     * @param query The query string to search on.
     */
    public FindCommand(String query) {
        this.query = query;
    }

    /**
     * Find and return any tasks that contain the given query string.
     *
     * @param tasks The TaskList of the Roger program.
     * @param storage The storage to read and load to.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        List<Task> tasksMatched = tasks.search(this.query);

        if (tasksMatched.isEmpty()) {
            return "No tasks matching that string.";
        }

        StringBuilder response = new StringBuilder("Here are the matching tasks in your list:\n");
        for (Task task: tasksMatched) {
            response.append(String.valueOf(tasks.getTaskNum(task)) + ". " + task.toString() + "\n");
        }
        return response.toString();
    }
}
