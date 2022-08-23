package duke.command;

import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;

public class InvalidCommand extends Command {
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showInvalidCommand();
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof InvalidCommand;
    }
}
