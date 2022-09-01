package commands;

import ui.Ui;

public class UnrecognisedCommand extends Command {

    private Ui ui;

    public UnrecognisedCommand(Ui ui) {
        this.ui = ui;
    }

    @Override
    public boolean execute() {
        ui.showCommandUnknown();
        return true;
    }
}
