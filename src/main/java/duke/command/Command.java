package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.ui.Ui;

/**
 * Parent of all specific Commands.
 */
public abstract class Command {
    public abstract String execute(TaskList tasks, Ui ui, Storage storage);

    public boolean isExit() {
        return false;
    }
}
