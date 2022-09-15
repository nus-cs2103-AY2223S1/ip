package duke.commands;

import duke.ui.Ui;

public class HelpCommand extends Command {

    private Ui ui;

    public HelpCommand(Ui ui) {
        this.ui = ui;
    }

    public String execute() {
        return ui.printOnHelp();
    }
}
