package roger.commands;

import roger.storage.Storage;
import roger.tasks.Task;
import roger.tasks.TaskList;

/**
 * Encapsulates the command to list all tasks.
 */
public class ListCommand extends Command {

    /**
     * List all tasks.
     *
     * @param tasks The TaskList of the Roger program.
     * @param storage The storage to read and load to.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        if (tasks.isEmpty()) {
            return "No tasks. Nephew must be a failure.";
        }

        StringBuilder response = new StringBuilder("Nephew got a lot of things to do:\n");

        for (int taskNum = 1; taskNum < tasks.getLength() + 1; ++taskNum) {
            Task task = tasks.get(taskNum);
            response.append(String.valueOf(taskNum) + ". " + task.toString() + "\n");
        }
        return response.toString();
    }
}
