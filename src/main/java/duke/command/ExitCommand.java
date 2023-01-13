package duke.command;

import duke.Response;
import duke.Storage;
import duke.TaskList;

/**
 * Represents a command to exit the application.
 */
public class ExitCommand extends Command{

    /**
     * Initialises the exit command.
     */
    public ExitCommand() {
    }

    /**
     * Executes this command.
     * @param tasks Task list used in application.
     * @param storage Storage used in application.
     * @return The response of the execution.
     */
    @Override
    public Response execute(TaskList tasks, Storage storage) {
        return new Response("Bye. Hope to see you again soon!", true);
    }
}
