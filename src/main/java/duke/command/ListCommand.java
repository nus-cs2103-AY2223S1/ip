package duke.command;

import duke.response.Response;
import duke.task.TasksList;

/**
 * Represents a command to add display the current tasks in the TasksList.
 */
public class ListCommand extends Command {
    private TasksList tasksList;

    /**
     * Creates a new ListCommand instance.
     * @param tasksList The TasksList to be displayed.
     */
    public ListCommand(TasksList tasksList) {
        this.tasksList = tasksList;
    }

    /**
     * Returns the string representing the TasksList, wrapped in a Response.
     * @return Response wrapping the string representation of the TasksList.
     */
    @Override
    public Response execute() {
        assert this.tasksList != null : "The taskslist should not be null";
        return new Response(this.tasksList.toString());
    }
}
