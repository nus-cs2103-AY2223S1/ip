package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ExitCommand implements ICommand {
    @Override
    public void execute(Storage storage, TaskList taskList, Ui ui) {
        Ui.showMsg("Thank you!");
    }

    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof ExitCommand;
    }
}
