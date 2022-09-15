package duke.commands;

import duke.ui.Ui;

public class UnrecognisedCommand extends Command {

    private Ui ui;

    public UnrecognisedCommand(Ui ui) {
        this.ui = ui;
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
