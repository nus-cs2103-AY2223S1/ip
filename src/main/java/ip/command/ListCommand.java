package ip.command;

import ip.utility.TaskList;

/**
 * DukeCommand to list all tasks in the task list.
 */
public class ListCommand extends DukeCommand {
    /**
     * Lists all tasks in the given task list.
     *
     * @param taskList The task list to list out.
     */
    @Override
    public String execute(TaskList taskList) {
        return taskList.listAllTasks();
    }
}
