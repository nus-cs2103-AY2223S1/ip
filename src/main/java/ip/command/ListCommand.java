package ip.command;

import ip.TaskList;
import ip.task.Task;

/**
 * Command to list all tasks in the task list.
 */
public class ListCommand extends Command {

    /**
     * Lists all tasks in the given task list.
     *
     * @param taskList The task list to list out.
     */
    @Override
    public void execute(TaskList taskList) {
        int i = 1;
        for (Task task : taskList.tasks) {
            System.out.println(i + ". " + task);
            i++;
        }
        System.out.println(taskList);
    }
}
