package duke.command;

import duke.Response;

/**
 * WelcomeCommand represents a command to greet the user.
 */
public class WelcomeCommand extends Command {
    private static final String GREETING = "Hello! I am duke.\n" + "What can I do for you?\n";

    /**
     * Displays the welcome message.
     *
     * @return The Response to be displayed.
     */
    @Override
    public Response execute() {
        return new Response(GREETING);
    }
}
