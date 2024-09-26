package duke.command;

import duke.Response;

/**
 * ExitCommand represents a command to exit the application.
 */
public class ExitCommand extends Command {
    private static final String GOODBYE = "Bye. Hope to see you again soon!\n";

    /**
     * Displays the goodbye message.
     *
     * @return The Response to be displayed.
     */
    @Override
    public Response execute() {
        Response exitResponse = new Response(GOODBYE);
        exitResponse.setExitResponse();
        return exitResponse;
    }
}
