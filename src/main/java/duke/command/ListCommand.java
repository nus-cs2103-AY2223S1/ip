package duke.command;

import duke.Response;
import duke.task.TaskList;

/**
 * ListCommand represents a command to display the tasks in the TaskList.
 */
public class ListCommand extends Command {
    private TaskList taskList;

    /**
     * Creates a ListCommand to display the tasks in the TaskList.
     * @param taskList The TaskList to display.
     */
    public ListCommand(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Displays the Tasks in the TaskList.
     * @return The Response to be displayed.
     */
    @Override
    public Response action() {
        return new Response(this.taskList.toString());
    }
}
