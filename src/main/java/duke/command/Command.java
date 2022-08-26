package duke.command;

import duke.data.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

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
