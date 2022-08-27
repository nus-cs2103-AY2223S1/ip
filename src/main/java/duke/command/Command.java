package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Parent of all specific Commands.
 */
public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);

    public boolean isExit() {
        return false;
    }
}
