package duke.commands;

/**
 * A command that represents an unrecognised command.
 */
public class UnrecognisedCommand extends Command {

    private String cmdString;

    /**
     * Constructs a new unrecognised command.
     * @param cmdString The string of the unrecognised command.
     */
    public UnrecognisedCommand(String cmdString) {
        this.cmdString = cmdString;
    }

    /**
     * Returns a response indicating that the command is invalid.
     * @return Response message.
     */
    @Override
    public String execute() {
        String response = String.format(
                "Oops! I'm sorry, but I don't know what \"%s\" means.",
                cmdString);
        return response;
    }

    /**
     * Does nothing.
     * @return A string indicating that there is nothing to undo.
     */
    @Override
    public String undo() {
        return "There is nothing to undo for your previous unrecognised command.";
    }

}
