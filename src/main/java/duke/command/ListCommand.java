package duke.command;

import duke.logic.TaskList;

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
    @Override
    public void run() {
        System.out.println("Here are the tasks in your list:");
        System.out.print(this.taskList.toString());
    }
}
