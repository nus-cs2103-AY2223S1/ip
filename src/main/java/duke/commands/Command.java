package duke.commands;

import duke.others.DukeException;
import duke.storage.Storage;
import duke.storage.TaskList;
import duke.ui.Ui;

public abstract class Command {

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        throw new DukeException("This method is to be implemented by child classes.");
    }

    public abstract boolean isRunning();

}
