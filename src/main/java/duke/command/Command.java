package duke.command;

import duke.task.*;
import duke.ui.Ui;
import duke.data.Storage;

public abstract class Command {

    public abstract void execute(TaskList taskList, Ui ui, Storage storage);

    /**
     * Returns True if is a exit command. False otherwise.
     *
     * @return boolean.
     */
    public boolean isExit() {
        return false;
    }
}
