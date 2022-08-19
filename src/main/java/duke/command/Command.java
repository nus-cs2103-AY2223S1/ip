package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public abstract class Command {
    public abstract void execute(Storage storage, TaskList taskList, Ui ui);

    public boolean isExit() {
        return false;
    }
}
