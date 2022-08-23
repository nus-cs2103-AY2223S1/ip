package duke.command;

import duke.Ui;

public class ExitCommand implements ICommand {
    @Override
    public void execute() {
        Ui.showMsg(new StringBuilder("Thank you!"));
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
