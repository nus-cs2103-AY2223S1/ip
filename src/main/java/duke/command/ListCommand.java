package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;

/**
 * Command to list all tasks currently in the list.
 */
public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";

    /**
     * Lists all tasks in the tasks list.
     *
     * @param tasks List of tasks.
     * @param storage Storage for the task list.
     * @return A String that lists all the tasks in the task list.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        StringBuilder sb = new StringBuilder("Here are the tasks in your list:");
        for (int i = 1; i <= tasks.size(); i++) {
            sb.append("\n\t").append(i).append(".").append(tasks.getTask(i));
        }
        return sb.toString();
    }
}
