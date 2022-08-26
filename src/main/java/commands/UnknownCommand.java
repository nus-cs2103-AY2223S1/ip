package commands;

import duke.Ui;

public class UnknownCommand extends Command {

    private Ui ui;
    public UnknownCommand(Ui ui) {
        this.ui = ui;
    }

    public void execute() {
        ui.showUnknown();
    }
}
