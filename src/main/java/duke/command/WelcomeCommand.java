package duke.command;

import duke.response.Response;

/**
 * Represents a command to greet the user.
 */
public class WelcomeCommand extends Command {
    private static final String WELCOME_MSG = "Hello! I'm Duke.\nWhat can I do for you?";


    /**
     * Greets the user.
     * @return The Response to be displayed upon the execution of the command.
     */
    @Override
    public Response execute() {
        return new Response(WelcomeCommand.WELCOME_MSG);
    }
}
