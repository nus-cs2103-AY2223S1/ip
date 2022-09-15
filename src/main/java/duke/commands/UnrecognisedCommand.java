package duke.commands;

public class UnrecognisedCommand extends Command {

    public UnrecognisedCommand() {
    }

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
