package command;

import duke.Ui;

public class ByeCommand extends Command {
    Ui ui;

    public ByeCommand() {
        this.ui = new Ui();
    }

    @Override
    public String execute() {
        return ui.goodbyeMessage();
    }
}
