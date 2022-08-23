package duke.command;

import duke.task.*;
import duke.ui.Ui;
import duke.data.Storage;

public abstract class Command {

    public abstract void execute(TaskList taskList, Ui ui, Storage storage);

    public boolean isExit() {
        return false;
    }
}
