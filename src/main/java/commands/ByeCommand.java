package commands;

import duke.Ui;

public class ByeCommand extends Command {

    private Ui ui;

    public ByeCommand(Ui ui) {
        this.ui = ui;
    }

    public void execute() {
        ui.showBye();
    }
}
