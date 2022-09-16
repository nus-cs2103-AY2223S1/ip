package duke.commands;

public class UnrecognisedCommand extends Command {

    public UnrecognisedCommand() {
    }

    /**
     * Returns a response indicating that the command is invalid.
     * @return Response message.
     */
    @Override
    public String execute() {
        String response = "Oops! I'm sorry, but I don't know what that means.";
        return response;
    }

    @Override
    public String undo() {
        return "There is nothing to undo for your previous unrecognised command.";
    }

}
