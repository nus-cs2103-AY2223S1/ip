package duke.command;

import duke.ExitResponse;
import duke.Response;

/**
 * Represents a command to exit the program.
 */
public class ExitCommand extends Command {

    /**
     * Returns the goodbye Response.
     * @return The Response to be displayed upon the execution of the command.
     */
    @Override
    public Response execute() {
        return new ExitResponse();
    }

}
