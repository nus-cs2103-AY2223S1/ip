package duke.command;

import duke.Response;
import duke.Storage;
import duke.TaskList;

/**
 * Represents a command to list all current tasks.
 */
public class ListCommand extends Command{

    /**
     * Initialises the list command.
     */
    public ListCommand() {
    }

    /**
     * Executes this command.
     * @param tasks Task list to be listed.
     * @param storage Storage used in application.
     * @return The response of the execution.
     */
    @Override
    public Response execute(TaskList tasks, Storage storage) {
        return new Response(tasks.toString(), false);
    }
}
