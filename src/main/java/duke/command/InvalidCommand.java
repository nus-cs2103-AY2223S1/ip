package duke.command;

import duke.Response;

/**
 * InvalidCommand represents a command that is invalid.
 */
public class InvalidCommand extends Command {
    private static final String INVALID = "OOPS!!! ";
    private String message;

    /**
     * Creates a InvalidCommand to inform the user that the command is invalid.
     *
     * @param message The error message.
     */
    public InvalidCommand(String message) {
        this.message = message;
    }

    /**
     * Returns an error message.
     *
     * @return The Response to be displayed.
     */
    @Override
    public Response execute() {
        return new Response(INVALID + this.message + "\n");
    }
}
