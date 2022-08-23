package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class EmptyCommand implements ICommand {
    @Override
    public void execute(Storage storage, TaskList taskList, Ui ui) {
        return;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
