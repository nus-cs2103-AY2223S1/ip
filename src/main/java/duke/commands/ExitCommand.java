package duke.commands;

import duke.ui.Ui;

public class ExitCommand extends Command {

    private Ui ui;

    public ExitCommand(Ui ui) {
        this.ui = ui;
    }

    @Override
    public String execute() {
        System.exit(0);
        return "";
    }

    @Override
    public String undo() {
        return "";
    }

}
