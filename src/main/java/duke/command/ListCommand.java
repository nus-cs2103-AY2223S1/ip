package duke.command;

import duke.logic.TaskList;
import duke.task.Task;

/**
 * ListCommand is a command for Duke to list the task list.
 *
 * @author totsukatomofumi
 */
public class ListCommand extends Command {
    /** Task list to be listed for user. */
    private TaskList taskList;

    /**
     * Constructor for the command.
     *
     * @param taskList the task list to be listed for user.
     */
    public ListCommand(TaskList taskList) {
        super();
        this.taskList = taskList;
    }

    /**
     * Lists the tasks in the user task list.
     */
    public void run() {
        int order = 1;
        System.out.println("Here are the tasks in your list:");
        for (Task task : taskList) {
            System.out.println(order++ + "." + task.toString());
        }
    }
}
