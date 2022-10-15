package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Generic Command parent class to represent a command that the user provides.
 */
public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    public boolean isExit() {
        return false;
    }
}
