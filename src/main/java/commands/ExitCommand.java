package commands;

import ui.Ui;

public class ExitCommand extends Command {

    private Ui ui;

    public ExitCommand(Ui ui) {
        this.ui = ui;
    }

    @Override
    public boolean execute() {
        ui.showBye();
        System.exit(0);
        return true;
    }
}
