package ip.command;

import ip.utility.Storage;
import ip.utility.TaskList;

/**
 * DukeCommand to list all tasks in the task list.
 */
public class ListCommand extends DukeCommand {
    /**
     * Lists all tasks in the given task list.
     *
     * @param taskList The task list to list out.
     * @param storage
     */
    @Override
    public String execute(TaskList taskList, Storage storage) {
        String tasks = taskList.listAllTasks();
        if (tasks.isEmpty()) {
            return "You have no tasks. Add some now!";
        }
        return "Your outstanding tasks:\n" + tasks;
    }
}
