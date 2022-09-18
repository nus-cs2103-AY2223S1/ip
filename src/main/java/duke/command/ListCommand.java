package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;

/**
 * Command to list out all the current tasks in the task list.
 */
public class ListCommand extends Command {
    @Override
    public String getResponse(TaskList tasks, Storage storage) {
        if (tasks.getNumberOfTasks() == 0) {
            return "Yay! You don't have any tasks in your list.";
        }
        return "This is your task list:\n" + tasks.toString();
    }

    /**
     * Check if the command exit duke.
     * @return
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
